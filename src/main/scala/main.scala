import shapeless._
import shapeless.ops.traversable._

object Filter {
  def apply[A <: HList, O <: HList]()(implicit ftA: FromTraversable[A],
                                      ftO: FromTraversable[O]): Unit = ()
}
object Main {
  def main = Filter[HNil, HNil]()
}
