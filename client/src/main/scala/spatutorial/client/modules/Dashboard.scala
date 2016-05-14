package spatutorial.client.modules

import diode.react._
import diode.data.Pot
import japgolly.scalajs.react.ReactComponentB
import japgolly.scalajs.react.extra.router.RouterCtl
import japgolly.scalajs.react.vdom.prefix_<^._
import spatutorial.client.SPAMain.{Loc, TodoLoc}
import spatutorial.client.components._
import spatutorial.client.services.RootModel
import ReactFlexBoxGrid._



object Dashboard {

  case class Props(router: RouterCtl[Loc], proxy: ModelProxy[Pot[String]])

  // create dummy data for the chart
  val cp = Chart.ChartProps("Test chart", Chart.BarChart, ChartData(Seq("A", "B", "C"), Seq(ChartDataset(Seq(1, 2, 3), "Data1"))))

  // create the React component for Dashboard
  private val component = ReactComponentB[Props]("Dashboard")
    .render_P { case Props(router, proxy) =>
      <.div(
        // header, MessageOfTheDay and chart components
        <.h2("Dashboard"),
        // use connect from ModelProxy to give Motd only partial view to the model
        proxy.connect(m => m)(Motd(_)),
        Chart(cp),
        // create a link to the To Do view
        <.div(router.link(TodoLoc)("Check your todos!")),
        Grid(Grid.Props(true))(
          Row(Row.Props(Map(XS → Top, SM → First, MD → Last, LG → Bottom)))(
            Column(Column.Props(Map(XS → DeclareColumn(AutoWidth), SM → DeclareColumn(Some(6)), MD → DeclareColumn(AutoWidth) )))(
              <.div(^.backgroundColor := "#f3f3f3")("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n")
            ),
            Column(Column.Props(Map(XS → DeclareColumn(Some(3)), SM → DeclareColumn(Some(2)), MD → DeclareColumn(AutoWidth) )))(
              <.div(^.backgroundColor := "#f3f3f3")("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n")
            ),
            Column(Column.Props(Map(XS → DeclareColumn(Some(3)), SM → DeclareColumn(Some(6)), MD → DeclareColumn(AutoWidth) )))(
              <.div(^.backgroundColor := "#f3f3f3")("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n")
            ),
            Column(Column.Props(Map(XS → DeclareColumn(AutoWidth), SM → DeclareColumn(AutoWidth), MD → DeclareColumn(AutoWidth) )))(
              <.div(^.backgroundColor := "#f3f3f3")("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n")
            ),
            Column(Column.Props(Map(XS → DeclareColumn(AutoWidth), SM → DeclareColumn(AutoWidth), MD → DeclareColumn(AutoWidth) )))(
              <.div(^.backgroundColor := "#f3f3f3")("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n")
            )
          )
        )
      )
    }.build

  def apply(router: RouterCtl[Loc], proxy: ModelProxy[Pot[String]]) = component(Props(router, proxy))
}
