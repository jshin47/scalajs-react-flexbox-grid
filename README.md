# scalajs-react-flexbox-grid

This is a port of `flexboxgrid`, a rather simplistic grid CSS library, to ScalaJS. The port is so elementary that it may not seem all that useful, but that's partially because of how well-designed the `flexboxgrid` library is.

This is a very easy component for beginners to understand and use, because it actually involves no native JS calls (it's a CSS library, once again). So that means the whole thing is implemented by applying styles appropriately and tying them to case classes.

I just created this so I will be cleaning things up. I ported the intro to scalajs boilerplate as a starting point but I didn't fork because this is not going to be a boilerplate, but just this component and a demo.

## What does it do?

This library allows you to create `flexboxgrid` wrappers using `ReactComponentB`'s and `Props` for each type of thing. If it is working, you can do all sorts of neat stuff with the library without needing to write a single double-quote.

```
set EclipseKeys.skipParents in ThisBuild := false
eclipse
```

## Credit/Praise for the boilerplate

This is a dinky little library whose type-safety is a lie because at the end of the day, you're relying on CSS class names. But I've used a form of it for a while and found it useful, so I wrote this tonight as a way to get started contributing with something similar to something I have used.

All praise is due to [scalajs-spa-tutorial](https://github.com/ochrons/scalajs-spa-tutorial) for this wonderful boilerplate. I do plan to strip most away but this is a great way to get off the ground.

Actually, I take it back. A lot of praise is due to the boilerplate, but I think [Li Haoyi](www.lihaoyi.com/scala-js-games/) probably gets "all praise" if anyone does. But still, thanks for the boilerplate.

# Scala IDE users

If you are using Scala IDE, you need to set additional settings to get your Eclipse project exported from SBT.


