# MarkerView

<img width=400 height=200 src="image.png"/>

> A draggable marker view for Android

[![](https://jitpack.io/v/realpacific/marker-view.svg)](https://jitpack.io/#realpacific/marker-view)


## Installation

In build.gradle

```
allprojects {
   repositories {
	...
	maven { url 'https://jitpack.io' }
   }
}

dependencies {
    implementation 'com.github.realpacific:marker-view:latest'
}
```

## Usage

<ol>

<li> 
Define a <code>MarkerConfig</code>.

For a `MarkerView` with text:

```
val config = MarkerConfig()
    .withColor(R.color.colorPrimaryDark)
    .withSize(70)
    .withText("Z", android.R.color.white)
    .withDraggableListener(listener)
```

Or for `MarkerView` with a Punch hole

```
val config = MarkerConfig()
    .withColor(R.color.colorPrimaryDark)
    .withSize(70)
    .withPunchHole
    .withDraggableListener(listener)
```

</li>

<b>Note</b>: Pass in an instance of `DraggableListener` to `MarkerConfig#withDraggableListener` to make the `MarkerView` dragable.

<b>Note</b>: You can use `MarkerConfig#clone` to copy the config and modify the newly created `MarkerConfig`.

<li> Create a <code>MarkerView</code> passing in above <code>config</code>.

```
val markerView = MarkerView(context, config)
```

</li>

<li>Lastly add it to your layout.

```
val container = context.findViewById<FrameLayout>(R.id.container)
container.addView(markerView)
```

</li>

</ol>

## License

```
MIT License

Copyright (c) 2020 Prashant Barahi

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

```
