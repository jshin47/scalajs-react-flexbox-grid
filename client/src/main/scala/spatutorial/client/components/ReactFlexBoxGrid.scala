package spatutorial.client.components

import japgolly.scalajs.react.{BackendScope, PropsChildren}

import scala.collection.immutable.HashMap
import scala.scalajs.js
import scala.scalajs.js.annotation.JSName
import japgolly.scalajs.react._
import japgolly.scalajs.react.extra.router._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.reflect.ClassTag

@js.native
@JSName("flexboxgrid")
object FlexBoxGridJS extends js.Object {

  val style: js.Dynamic = js.native

}

object ReactFlexBoxGrid {

  trait RendersCustomTag {
    def tagName: String
  }

  abstract class MapsToClassNames {
    def toClassNames: Seq[String]
  }

  def flexBoxGridStyle(named: String) = FlexBoxGridJS.style(named).toString
  def containerOrFluid(fluid: Boolean) = s"container${if (fluid) "-fluid"}"
  def reverseClassNameAsSequence(reverse: Boolean) = Seq(reverse).filter(x ⇒ x).map(x ⇒ "reverse")

  sealed trait ScreenBreakpoint

  abstract class _ScreenBreakpoint(override val toString: String) extends ScreenBreakpoint

  case object XS extends _ScreenBreakpoint("xs")
  case object SM extends _ScreenBreakpoint("sm")
  case object MD extends _ScreenBreakpoint("md")
  case object LG extends _ScreenBreakpoint("lg")

  abstract class ColumnPositioning(override val toString: String)

  case object Start extends ColumnPositioning("start")
  case object Center extends ColumnPositioning("center")
  case object End extends ColumnPositioning("end")
  case object Top extends ColumnPositioning("top")
  case object Middle extends ColumnPositioning("middle")
  case object Bottom extends ColumnPositioning("bottom")
  case object Around extends ColumnPositioning("around")
  case object Between extends ColumnPositioning("between")
  case object First extends ColumnPositioning("first")
  case object Last extends ColumnPositioning("last")

  final val AutoWidth: Option[Int] = None
  case class DeclareColumn(width: Option[Int] = AutoWidth) {
    def manualWidth: String = width match {
      case AutoWidth ⇒ ""
      case Some(manualWidth) ⇒ s"-$manualWidth"
    }
  }

  final val NoOffset: Option[Int] = None
  case class ColumnOffset(off: Option[Int] = NoOffset) {
    def manualOffset: String = off match {
      case NoOffset ⇒ ""
      case Some(offset) ⇒ s"-offset-$offset"
    }
  }

  implicit class MapsToScreenBreakpoints[T](hash: Map[ScreenBreakpoint, T]) {

    def toClassNames(func: (T) ⇒ Option[(ScreenBreakpoint) ⇒ String]): Seq[String] = hash.foldLeft(Seq[String]()) {
      case (seq, (b, t)) ⇒ func(t) match {
        case Some(curried) ⇒ seq :+ curried(b)
        case None        ⇒ seq
      }
    }

  }

  implicit class ColumnPositionsByBreakpoint(hash: Map[ScreenBreakpoint, ColumnPositioning]) extends MapsToScreenBreakpoints[ColumnPositioning](hash) {
    val func: (ColumnPositioning ⇒ Option[(ScreenBreakpoint) ⇒ String]) = colPos ⇒ Some(b ⇒ s"$colPos-$b")
    def toClassNames: Seq[String] = toClassNames(func)
  }
  implicit class ColumnWidthsByBreakpoint(hash: Map[ScreenBreakpoint, DeclareColumn]) extends MapsToScreenBreakpoints[DeclareColumn](hash) {
    val func: (DeclareColumn ⇒ Option[(ScreenBreakpoint) ⇒ String]) = w ⇒ Some(b ⇒ s"col-$b${w.manualWidth}")
    def toClassNames: Seq[String] = toClassNames(func)
  }
  implicit class ColumnOffsetsByBreakpoint(hash: Map[ScreenBreakpoint, ColumnOffset]) extends MapsToScreenBreakpoints[ColumnOffset](hash) {
    val func: (ColumnOffset ⇒ Option[(ScreenBreakpoint) ⇒ String]) = w ⇒ Some(b ⇒ s"col-$b${w.manualOffset}")
    def toClassNames: Seq[String] = toClassNames(func)
  }

  class Backend[TProps <: MapsToClassNames with RendersCustomTag]($: BackendScope[TProps, Unit]) {
    def render(P: TProps, C: PropsChildren) =
      P.tagName.reactTag(^.className := P.toClassNames.mkString(" ").toLowerCase)(C)
  }

  private def c[TProps <: MapsToClassNames with RendersCustomTag](name: String) =
    ReactComponentB[TProps](name)
      .stateless
      .renderPC( ($, P: TProps,  C: PropsChildren) ⇒
        P.tagName.reactTag(^.className := P.toClassNames.mkString(" "))(C)
      )
      .build

  object Grid {
    case class Props(
                      fluid:     Boolean = false,
                      className: String  = "",
                      tagName:   String  = "div"
                    )
      extends MapsToClassNames
        with RendersCustomTag {
      override def toClassNames: Seq[String] = Seq(className, containerOrFluid(fluid))
    }

    def apply(p: Props)(ch: ReactNode*) = c[Props]("FlexBoxGrid")(p, ch)
  }

  object Row {

    case class Props(
                         colPositioning: Map[ScreenBreakpoint, ColumnPositioning] = Map(),
                         reverse: Boolean              = false,
                         className: String             = "",
                         tagName:   String             = "div"
                       )
      extends MapsToClassNames
        with RendersCustomTag {

      def toClassNames: Seq[String] =

        Seq(className, "row") ++ colPositioning.toClassNames ++ reverseClassNameAsSequence(reverse)

    }

    def apply(p: Props)(ch: ReactNode*) = c[Props]("FlexBoxRow")(p, ch)

  }

  object Column {
    case class Props(
                         widths:  Map[ScreenBreakpoint, DeclareColumn] = Map(),
                         offsets: Map[ScreenBreakpoint, ColumnOffset]  = Map(),
                         reverse: Boolean         = false,
                         className: String        = "",
                         tagName: String          = "div"
                       )
      extends MapsToClassNames
        with RendersCustomTag {
      def toClassNames: Seq[String] = (className +: widths.toClassNames) ++ offsets.toClassNames ++ reverseClassNameAsSequence(reverse)
    }

    def apply(p: Props)(ch: ReactNode*) = c[Props]("FlexBoxCol")(p, ch)

  }



}
