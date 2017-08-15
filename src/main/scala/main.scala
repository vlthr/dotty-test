trait HList
case class HNil() extends HList
trait FromTraversable[Out <: HList] extends Serializable {
  def apply(l: Any): Option[Out]
}
object FromTraversable {
  def apply[Out <: HList](implicit from: FromTraversable[Out]) = from

  implicit def hnilFromTraversable[T]: FromTraversable[HNil] =
    new FromTraversable[HNil] {
      def apply(l: Any) = None
    }
}

object Filter {
  def apply[A <: HList, O <: HList]()(implicit ftA: FromTraversable[A],
                                      ftO: FromTraversable[O]): Unit = ()
}
object Main {
  def main = Filter[HNil, HNil]()
}
