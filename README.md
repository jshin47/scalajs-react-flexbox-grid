# scalajs-react-flexbox-grid

This is a port of `flexboxgrid`, a rather simplistic grid CSS library, to ScalaJS. The port is so elementary that it may not seem all that useful, but that's partially because of how well-designed the `flexboxgrid` library is.

This is a very easy component for beginners to understand and use, because it actually involves no native JS calls (it's a CSS library, once again). So that means the whole thing is implemented by applying styles appropriately and tying them to case classes.

I just created this so I will be cleaning things up. I ported the intro to scalajs boilerplate as a starting point but I didn't fork because this is not going to be a boilerplate, but just this component and a demo.

![logo](https://raw.githubusercontent.com/jshin47/scalajs-react-flexbox-grid/master/terribledemo.png)

## What does it do?

This library allows you to create `flexboxgrid` wrappers using `ReactComponentB`'s and `Props` for each type of thing. If it is working, you can do all sorts of neat stuff with the library without needing to write a single double-quote.

```
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
```

## Where is it?

It's in one file called `ReactFlexBoxGrid.scala` in `client`. Obviously this is not even close to publishable so if you want to use it or start from it, clone or just copy that file. See the home page for a crappy demo

## Credit/Praise for the boilerplate

This is a dinky little library whose type-safety is a lie because at the end of the day, you're relying on CSS class names. But I've used a form of it for a while and found it useful, so I wrote this tonight as a way to get started contributing with something similar to something I have used.

All praise is due to [scalajs-spa-tutorial](https://github.com/ochrons/scalajs-spa-tutorial) for this wonderful boilerplate. I do plan to strip most away but this is a great way to get off the ground.

Actually, I take it back. A lot of praise is due to the boilerplate, but I think [Li Haoyi](www.lihaoyi.com/scala-js-games/) probably gets "all praise" if anyone does. But still, thanks for the boilerplate.

# Make react great again!


