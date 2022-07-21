+  scan svg path with javafx 2d shapes
+ possibility to move around created images with "wasd" or arrow keys and with enter pressed the images maximises
+ doubleclick on selected art to maximes it
    + have an clickable x to return to overview
+ textfield can only take integer
    + else : show on label or popup window that you have input the wrong type COLOR whole hbox red when its unequal int

### features
    - save selection as svg or png or jpg
    - save selected items as single images
        + define resolution for them to export those
    - edit nodes where those shapes center around
    - folder where user can put b&w images into that will get through the process of making new shapoes with them (svg / png)



### generate algorithm
 - 3-10 random iteration with random selection of: union, subtract, intersect
 - mirror part of it
 - have nodes at specific x,y areas where those iterations will take place to make it always look for example : humanoid, creature ..
 - train ml on it
 #### algo 2
 - generate svg file build after ref with random numbers 

 ## extra
 - generate shapes along a path (humanoid) , they are stored in a folder and can
 - make the create method a strategy pattern

 ### shape generation by 3d shapes
 - user  can put own 3d shape into 3d_shapes directory to use them

### shape-editor // node ediot
- a single window where the user can create a random shape by pressing buttons or moving already existing shapes with the mouse (and then generate new ones over the moved ones)
- manipulation methods like flip(), mirrorX(), mirrorY(), intersectGroups(), substractGroups(), unionGroups(),
- generate shapes around specific nodes and lines where they are connected from
- let people make prefabs and save them to a list that is reachable within normal creation window

## architecture 
- make shapes to interfaces

res:
https://github.com/karldd/Alchemy/ !
https://github.com/Climenty/svg-join
https://www.demo2s.com/java/javafx-combining-shapes.html
https://edencoding.com/svg-javafx/
https://www.w3.org/Graphics/SVG/   => https://www.w3.org/TR/SVG11/
https://www.hameister.org/JavaFX_SVGPath.html
https://docs.oracle.com/javafx/2/canvas/jfxpub-canvas.htm
https://stackoverflow.com/questions/21973777/easiest-way-to-read-in-svg-path-data-with-java !
https://github.com/karldd/Alchemy/blob/master/src/org/alchemy/create/PullShapes.java !
https://stackoverflow.com/questions/38994519/responsive-ui-design-with-a-tilepane-in-a-scrollpane !

https://mavenlibs.com/jar/file/org.apache.xmlgraphics/batik-svggen

ref:
https://80.lv/articles/brainstorm-an-easier-way-to-come-up-with-ideas/
https://libgdx.com/
https://stackoverflow.com/questions/33961606/javafx-how-to-drag-object-along-given-svg-path-like-a-slider

useful libs:
https://xmlgraphics.apache.org/batik/using/
https://www.jfree.org/jfreesvg/javadoc/org.jfree.svg/org/jfree/svg/package-summary.html
