import shapeless._
import shapeless.ops.hlist.HKernelAux
import shapeless.ops.traversable._
import shapeless.syntax.typeable._

package object main {
  object Filter {
    def apply[I, A <: HList, O <: HList](name: String)(f: (Context, Filter, I, A, O) => Unit)(implicit ftArgs: FromTraversable[A], hkArgs: HKernelAux[A], ftOpt: FromTraversable[O]): Filter = new Filter {
      type Input = I
      type Args = A
      type OptArgs = O
      def name = name
      def filter(input: Input, args: Args, optArgs: OptArgs)(implicit ctx: Context): Unit = f(ctx, this, input, args, optArgs)
      def apply(input: Value, allArgs: List[Value])(implicit ctx: Context): Unit = {
        val (args, optArgs) = allArgs.splitAt(intLen[Args])
        val i = input.cast[Input].get
        val a = ftArgs(args).get
        val o = ftOpt(optArgs).get
        filter(i, a, o)
      }
    }

    def unknownFilter(name: String) = Filter[HNil, HNil, HNil](name) { (ctx, self, input, args, optArgs) =>
      ()
    }
  }
  abstract trait Filter {
    type Input
    type Args <: HList
    type OptArgs <: HList
    def name: String
    def filter(input: Input, args: Args, optArgs: OptArgs)(implicit ctx: Context): ValidatedFragment[Value]
    def extensionType = "filter"
    def intLen[T <: HList](implicit ker: HKernelAux[T]): Int = ker().length
    def apply(input: Value, allArgs: List[Value])(implicit ctx: Context): ValidatedFragment[Value]
  }
}
