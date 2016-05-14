package spatutorial.client

import japgolly.scalajs.react.ReactDOM
import japgolly.scalajs.react.extra.router._
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom
import spatutorial.client.components.GlobalStyles
import spatutorial.client.components.ReactFlexBoxGrid._
import spatutorial.client.logger._
import spatutorial.client.modules._
import spatutorial.client.services.SPACircuit

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport
import scala.util.Random
import scalacss.Defaults._
import scalacss.ScalaCssReact._

@JSExport("SPAMain")
object SPAMain extends js.JSApp {

  // Define the locations (pages) used in this application
  sealed trait Loc

  case object DashboardLoc extends Loc

  case object TodoLoc extends Loc

  // configure the router
  val routerConfig = RouterConfigDsl[Loc].buildConfig { dsl =>
    import dsl._

    // wrap/connect components to the circuit
    (staticRoute(root, DashboardLoc) ~> renderR(ctl => SPACircuit.wrap(_.motd)(proxy => Dashboard(ctl, proxy)))
      | staticRoute("#todo", TodoLoc) ~> renderR(ctl => SPACircuit.connect(_.todos)(Todo(_)))
      ).notFound(redirectToPage(DashboardLoc)(Redirect.Replace))
  }.renderWith(layout)

  val Lorem = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n"
  val LoremSeq = Lorem.split(" ").toSeq
  def randomWord: String = LoremSeq(Random.nextInt(LoremSeq.length - 2)) + " "

  def renderRandomContent(min: Int, max: Int) =
    <.div(
      ^.backgroundColor :=   randomLightColorLowA
    )(<.p(Iterator.fill(math.abs(Random.nextInt(math.abs(max)) - Random.nextInt(math.abs(min))) + 1)(randomWord)))

  def rand255 = Random.nextInt(255)
  def rand1   = Random.nextDouble()


  def randomColor = s"rgba($rand255,$rand255,$rand255,$rand1)"
  def color(r: Int, g: Int, b: Int, a: Double) = s"rgba($r,$g,$b,$a)"
  def randomLightColorLowA = color(rand255, rand255, rand255, 0.2)
  def backgroundRandomColor = ^.backgroundColor := randomColor
  def textRandomColor = ^.color := randomColor
  def randomColorings = Seq(backgroundRandomColor, textRandomColor)
  val rcs = Iterator.fill(30)(randomColorings).toSeq

  val style1 = Seq(^.backgroundColor := "rgba(200, 20, 20, 0.5)", ^.color := "#222")
  val style2 = Seq(^.backgroundColor := "rgba(142, 244, 13, 0.8)", ^.color := "#2ab4ee")

  // base layout for all pages
  def layout(c: RouterCtl[Loc], r: Resolution[Loc]) = {
    <.div(
      // here we use plain Bootstrap class names as these are specific to the top level layout defined here
      <.nav(^.className := "navbar navbar-inverse navbar-fixed-top",
        <.div(^.className := "container",
          <.div(^.className := "navbar-header", <.span(^.className := "navbar-brand", "SPA Tutorial")),
          <.div(^.className := "collapse navbar-collapse",
            // connect menu to model, because it needs to update when the number of open todos changes
            SPACircuit.connect(_.todos.map(_.items.count(!_.completed)).toOption)(proxy => MainMenu(c, r.page, proxy))
          )
        )
      ),
      // currently active module is shown in this container
      <.div(^.className := "container", r.render()),
      <.h1("The grid is below. I'm tired and I didn't think this thru. This is the worst demo ever - please go to www.flexboxgrid.com and see the proper one, thx."),
      Grid(Grid.Props(fluid = true))(

        // Row 1 - two columns; equal on LG but not on MD

        <.h3("Columns equal on LG, unequal (9+3) below LG."),
        Row()(
          Column(md = DeclareColumn(9), lg = DeclareColumn.WidthAuto)(renderRandomContent(50, 80)),
          Column(md = DeclareColumn(3), lg = DeclareColumn.WidthAuto)(renderRandomContent(50, 80))
        ),

        <.h3("One thing you can do is vary the flexing by the screen size. So we will go to two-columns on XS, three on SM, and four above. Look where the Columns are laid out."),
        Row()(
          Column(xs = DeclareColumn.WidthOf(6), sm = DeclareColumn.WidthOf(4), md = DeclareColumn.WidthAuto)(<.div(^.backgroundColor := randomLightColorLowA)(<.h5("latest news"), renderRandomContent(50, 80))),
          Column(xs = DeclareColumn.WidthOf(6), sm = DeclareColumn.WidthOf(4), md = DeclareColumn.WidthAuto)(<.div(^.backgroundColor := randomLightColorLowA)(<.h5("gossip"), renderRandomContent(50, 80))),
          Column(xs = DeclareColumn.WidthOf(6), sm = DeclareColumn.WidthOf(4), md = DeclareColumn.WidthAuto)(<.div(^.backgroundColor := randomLightColorLowA)(<.h5("nightly news"), renderRandomContent(50, 80))),
          Column(xs = DeclareColumn.WidthOf(6), sm = DeclareColumn.WidthOf(4), smOffset = ColumnOffset.OffsetOf(4), mdOffset = ColumnOffset.OffsetOf(0), md = DeclareColumn.WidthAuto)(<.div(^.backgroundColor := randomLightColorLowA)(<.h5("late nite - notice the offset on SM"), renderRandomContent(50, 80)))
        ),
        <.h3("Columns equal everywhere, many of them, colored. However, we align the content differently depending on the breakpoint. Then we switch in second row of LG."),
        Row(xs = ColPos.Between, sm = ColPos.Around, md = ColPos.Center, lg = ColPos.Bottom)(
          Column(xs = DeclareColumn.WidthAuto)(renderRandomContent(50, 80)),
          Column(xs = DeclareColumn.WidthAuto)(renderRandomContent(50, 80)),
          Column(xs = DeclareColumn.WidthAuto)(renderRandomContent(50, 80)),
          Column(xs = DeclareColumn.WidthAuto)(renderRandomContent(50, 80)),
          Column(xs = DeclareColumn.WidthAuto)(renderRandomContent(50, 80)),
          Column(xs = DeclareColumn.WidthAuto)(renderRandomContent(50, 80)),
          Column(xs = DeclareColumn.WidthAuto)(renderRandomContent(50, 80)),
          Column(xs = DeclareColumn.WidthAuto)(renderRandomContent(50, 80)),
          Column(xs = DeclareColumn.WidthAuto)(renderRandomContent(50, 80)),
          Column(xs = DeclareColumn.WidthAuto)(renderRandomContent(50, 80)),
          Column(xs = DeclareColumn.WidthAuto)(renderRandomContent(50, 80)),
          Column(xs = DeclareColumn.WidthAuto)(renderRandomContent(50, 80))
        ),
        Row(xs = ColPos.Bottom, sm = ColPos.Start, md = ColPos.Around, lg = ColPos.Last)(
          Column(xs = DeclareColumn.WidthAuto)(renderRandomContent(50, 80)),
          Column(xs = DeclareColumn.WidthAuto)(renderRandomContent(50, 80)),
          Column(xs = DeclareColumn.WidthAuto)(renderRandomContent(50, 80)),
          Column(xs = DeclareColumn.WidthAuto)(renderRandomContent(50, 80)),
          Column(xs = DeclareColumn.WidthAuto)(renderRandomContent(50, 80)),
          Column(xs = DeclareColumn.WidthAuto)(renderRandomContent(50, 80)),
          Column(xs = DeclareColumn.WidthAuto)(renderRandomContent(50, 80)),
          Column(xs = DeclareColumn.WidthAuto)(renderRandomContent(50, 80)),
          Column(xs = DeclareColumn.WidthAuto)(renderRandomContent(50, 80)),
          Column(xs = DeclareColumn.WidthAuto)(renderRandomContent(50, 80)),
          Column(xs = DeclareColumn.WidthAuto)(renderRandomContent(50, 80)),
          Column(xs = DeclareColumn.WidthAuto)(renderRandomContent(50, 80))
        )
      )
    )
  }

  @JSExport
  def main(): Unit = {
    log.warn("Application starting")
    // send log messages also to the server
    log.enableServerLogging("/logging")
    log.info("This message goes to server as well")

    // create stylesheet
    GlobalStyles.addToDocument()
    // create the router
    val router = Router(BaseUrl.until_#, routerConfig)
    // tell React to render the router in the document body
    ReactDOM.render(router(), dom.document.getElementById("root"))
  }
}
