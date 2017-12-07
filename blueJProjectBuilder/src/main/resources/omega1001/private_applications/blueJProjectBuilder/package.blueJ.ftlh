<#assign benchWidth = 776>
#BlueJ package file
editor.fx.0.height=0
editor.fx.0.width=0
editor.fx.0.x=0
editor.fx.0.y=0
objectbench.height=164
objectbench.width=776
package.divider.horizontal=0.6
package.divider.vertical=0.6845018450184502
package.editor.height=364
package.editor.width=653
package.editor.x=414
package.editor.y=91
package.frame.height=600
package.frame.width=800
package.numDependencies=2
package.numTargets=${concount}
package.showExtends=true
package.showUses=true
project.charset=UTF-8
readme.height=58
readme.name=@README
readme.width=47
readme.x=10
readme.y=10

<#assign nr = 1>
<#assign heigth = 60>
<#assign width = 80>
<#assign startX =70>
<#assign x = 70>
<#assign y = 20>
<#assign spacing = 20>

<#list packageTargets as t>
target${nr}.height=${heigth}
target${nr}.name=${t}
target${nr}.type=PackageTarget
target${nr}.width=${width}
target${nr}.x=${x}
target${nr}.y=${y}
<#assign nr = nr+1>
<#assign x = x + width + spacing>
<#if (x + width) lte benchWidth>
	<#assign y = y + heigth + spacing>
	<#assign x = startX>
</#if>
</#list>

<#assign heigth = 50>
<#assign width = 100>

<#list fileTargets as t>
target${nr}.height=${heigth}
target${nr}.name=${t}
target${nr}.showInterface=false
target${nr}.type=ClassTarget
target${nr}.typeParameters=
target${nr}.width=${width}
target${nr}.x=${x}
target${nr}.y=${y}
<#assign nr = nr +1>
<#assign x = x + width + spacing>
<#if (x + width) lte benchWidth>
	<#assign y = y + heigth + spacing>
	<#assign x = startX>
</#if>
</#list>