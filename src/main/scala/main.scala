import shapeless._
import shapeless.ops.hlist.HKernelAux
import shapeless.ops.traversable._
import shapeless.syntax.typeable._

package object main {
  object Filter {
    def apply[A <: HList, O <: HList]()(f: (A, O) => Unit)(implicit ftArgs: FromTraversable[A], ftOpt: FromTraversable[O]): Filter = new Filter {
      def filter(args: A, optArgs: O): Unit = f(args, optArgs)
    }

    def unknownFilter() = Filter[HNil, HNil]() { (args, optArgs) =>
      ()
    }
  }
  abstract trait Filter {
  }
}
