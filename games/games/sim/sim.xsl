<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:param name="width" select="400"/>
<xsl:param name="height" select="400"/>

<xsl:template name="main" match="/">
  <div>
    <!-- Set Style -->
    <style type="text/css" media="all">
       td, tr, table {
      	margin: 0;
      	padding: 0;
      	border: 0;
      }
      td.cell {
        width:  <xsl:value-of select="$width * 0.02"/>px;
        height: <xsl:value-of select="$height * 0.02"/>px;
        background-color: #FFFFFF;
        border-style: none;
      }
      table.board {
        background-color: #FFFFFF;
        border-style: none;
        cellspacing:0;
      }
    </style>
    
    <!-- Draw Board -->
    <xsl:call-template name="board">
      <xsl:with-param name="cols" select="49"/>
      <xsl:with-param name="rows" select="51"/>
    </xsl:call-template>		    
  </div>  
  <xsl:if test="//fact[relation='line' and argument[1]=1 and argument[2]=2 and argument[3]='r']"> 
	<style>.cell.line12 {background-color: red;}</style></xsl:if>
	<xsl:if test="//fact[relation='line' and argument[1]=1 and argument[2]=3 and argument[3]='r']"> 
	<style>.cell.line13 {background-color: red;}</style></xsl:if>
	<xsl:if test="//fact[relation='line' and argument[1]=1 and argument[2]=4 and argument[3]='r']"> 
	<style>.cell.line14 {background-color: red;}</style></xsl:if>
	<xsl:if test="//fact[relation='line' and argument[1]=1 and argument[2]=5 and argument[3]='r']"> 
	<style>.cell.line15 {background-color: red;}</style></xsl:if>
	<xsl:if test="//fact[relation='line' and argument[1]=1 and argument[2]=6 and argument[3]='r']"> 
	<style>.cell.line16 {background-color: red;}</style></xsl:if>
	<xsl:if test="//fact[relation='line' and argument[1]=2 and argument[2]=3 and argument[3]='r']"> 
	<style>.cell.line23 {background-color: red;}</style></xsl:if>
	<xsl:if test="//fact[relation='line' and argument[1]=2 and argument[2]=4 and argument[3]='r']"> 
	<style>.cell.line24 {background-color: red;}</style></xsl:if>
	<xsl:if test="//fact[relation='line' and argument[1]=2 and argument[2]=5 and argument[3]='r']"> 
	<style>.cell.line25 {background-color: red;}</style></xsl:if>
	<xsl:if test="//fact[relation='line' and argument[1]=2 and argument[2]=6 and argument[3]='r']"> 
	<style>.cell.line26 {background-color: red;}</style></xsl:if>
	<xsl:if test="//fact[relation='line' and argument[1]=3 and argument[2]=4 and argument[3]='r']"> 
	<style>.cell.line34 {background-color: red;}</style></xsl:if>
	<xsl:if test="//fact[relation='line' and argument[1]=3 and argument[2]=5 and argument[3]='r']"> 
	<style>.cell.line35 {background-color: red;}</style></xsl:if>
	<xsl:if test="//fact[relation='line' and argument[1]=3 and argument[2]=6 and argument[3]='r']"> 
	<style>.cell.line36 {background-color: red;}</style></xsl:if>
	<xsl:if test="//fact[relation='line' and argument[1]=4 and argument[2]=5 and argument[3]='r']"> 
	<style>.cell.line45 {background-color: red;}</style></xsl:if>
	<xsl:if test="//fact[relation='line' and argument[1]=4 and argument[2]=6 and argument[3]='r']"> 
	<style>.cell.line46 {background-color: red;}</style></xsl:if>	
	<xsl:if test="//fact[relation='line' and argument[1]=5 and argument[2]=6 and argument[3]='r']"> 
	<style>.cell.line56 {background-color: red;}</style></xsl:if>
	
	<xsl:if test="//fact[relation='line' and argument[1]=1 and argument[2]=2 and argument[3]='b']"> 
	<style>.cell.line12 {background-color: blue;}</style></xsl:if>
	<xsl:if test="//fact[relation='line' and argument[1]=1 and argument[2]=3 and argument[3]='b']"> 
	<style>.cell.line13 {background-color: blue;}</style></xsl:if>
	<xsl:if test="//fact[relation='line' and argument[1]=1 and argument[2]=4 and argument[3]='b']"> 
	<style>.cell.line14 {background-color: blue;}</style></xsl:if>
	<xsl:if test="//fact[relation='line' and argument[1]=1 and argument[2]=5 and argument[3]='b']"> 
	<style>.cell.line15 {background-color: blue;}</style></xsl:if>
	<xsl:if test="//fact[relation='line' and argument[1]=1 and argument[2]=6 and argument[3]='b']"> 
	<style>.cell.line16 {background-color: blue;}</style></xsl:if>
	<xsl:if test="//fact[relation='line' and argument[1]=2 and argument[2]=3 and argument[3]='b']"> 
	<style>.cell.line23 {background-color: blue;}</style></xsl:if>
	<xsl:if test="//fact[relation='line' and argument[1]=2 and argument[2]=4 and argument[3]='b']"> 
	<style>.cell.line24 {background-color: blue;}</style></xsl:if>
	<xsl:if test="//fact[relation='line' and argument[1]=2 and argument[2]=5 and argument[3]='b']"> 
	<style>.cell.line25 {background-color: blue;}</style></xsl:if>
	<xsl:if test="//fact[relation='line' and argument[1]=2 and argument[2]=6 and argument[3]='b']"> 
	<style>.cell.line26 {background-color: blue;}</style></xsl:if>
	<xsl:if test="//fact[relation='line' and argument[1]=3 and argument[2]=4 and argument[3]='b']"> 
	<style>.cell.line34 {background-color: blue;}</style></xsl:if>
	<xsl:if test="//fact[relation='line' and argument[1]=3 and argument[2]=5 and argument[3]='b']"> 
	<style>.cell.line35 {background-color: blue;}</style></xsl:if>
	<xsl:if test="//fact[relation='line' and argument[1]=3 and argument[2]=6 and argument[3]='b']"> 
	<style>.cell.line36 {background-color: blue;}</style></xsl:if>
	<xsl:if test="//fact[relation='line' and argument[1]=4 and argument[2]=5 and argument[3]='b']"> 
	<style>.cell.line45 {background-color: blue;}</style></xsl:if>
	<xsl:if test="//fact[relation='line' and argument[1]=4 and argument[2]=6 and argument[3]='b']"> 
	<style>.cell.line46 {background-color: blue;}</style></xsl:if>	
	<xsl:if test="//fact[relation='line' and argument[1]=5 and argument[2]=6 and argument[3]='b']"> 
	<style>.cell.line56 {background-color: blue;}</style></xsl:if>
	
</xsl:template>

<xsl:template name="cell" match="state/fact">
  <xsl:param name="row" select="1"/>
  <xsl:param name="col" select="1"/> 

  <td>
  <xsl:attribute name="class">
  	<xsl:value-of select="'cell'"></xsl:value-of>
  	<xsl:if test="$row=38 and $col &lt;= 31 and $col &gt;= 18"><xsl:value-of select="' line12'"/></xsl:if>
  	<xsl:if test="$row=12 and $col &lt;= 31 and $col &gt;= 18"><xsl:value-of select="' line45'"/></xsl:if>
  	<xsl:if test="$row &lt;= 38 and $row &gt;= 25 and $row - $col=20"><xsl:value-of select="' line16'"/></xsl:if>
  	<xsl:if test="$row &lt;= 25 and $row &gt;= 12 and $col - $row=19"><xsl:value-of select="' line34'"/></xsl:if>
  	<xsl:if test="$row &lt;= 38 and $row &gt;= 25 and $row + $col=69"><xsl:value-of select="' line23'"/></xsl:if>
  	<xsl:if test="$row &lt;= 25 and $row &gt;= 12 and $row + $col=30"><xsl:value-of select="' line56'"/></xsl:if>
  	<xsl:if test="$row=25 and $col &lt;= 44 and $col &gt;= 5"><xsl:value-of select="' line36'"/></xsl:if>
  	<xsl:if test="$col=18 and $row &lt;= 38 and $row &gt;= 12"><xsl:value-of select="' line15'"/></xsl:if>
  	<xsl:if test="$col=31 and $row &lt;= 38 and $row &gt;= 12"><xsl:value-of select="' line24'"/></xsl:if>
  	<xsl:if test="$row &gt;= 12 and $row &lt;= 38 and 2 * $col - $row = 24"><xsl:value-of select="' line25'"/></xsl:if>
  	<xsl:if test="$row &gt;= 12 and $row &lt;= 38 and 2 * $col - $row = 25"><xsl:value-of select="' line25'"/></xsl:if>
  	<xsl:if test="$row &gt;= 12 and $row &lt;= 38 and 2 * $col + $row = 74"><xsl:value-of select="' line14'"/></xsl:if>
  	<xsl:if test="$row &gt;= 12 and $row &lt;= 38 and 2 * $col + $row = 73"><xsl:value-of select="' line14'"/></xsl:if>
  	<xsl:if test="$col &gt;= 18 and $col &lt;= 44 and 2 * $row - $col = 6"><xsl:value-of select="' line35'"/></xsl:if>
  	<xsl:if test="$col &gt;= 18 and $col &lt;= 44 and 2 * $row - $col = 5"><xsl:value-of select="' line35'"/></xsl:if>
  	<xsl:if test="$col &gt;= 5 and $col &lt;= 31 and 2 * $row - $col = 45"><xsl:value-of select="' line26'"/></xsl:if>
  	<xsl:if test="$col &gt;= 5 and $col &lt;= 31 and 2 * $row - $col = 46"><xsl:value-of select="' line26'"/></xsl:if>
  	<xsl:if test="$col &gt;= 18 and $col &lt;= 44 and 2 * $row + $col = 94"><xsl:value-of select="' line13'"/></xsl:if>
  	<xsl:if test="$col &gt;= 18 and $col &lt;= 44 and 2 * $row + $col = 95"><xsl:value-of select="' line13'"/></xsl:if>
  	<xsl:if test="$col &gt;= 5 and $col &lt;= 31 and 2 * $row + $col = 55"><xsl:value-of select="' line46'"/></xsl:if>
  	<xsl:if test="$col &gt;= 5 and $col &lt;= 31 and 2 * $row + $col = 56"><xsl:value-of select="' line46'"/></xsl:if>
  </xsl:attribute>
  
  <xsl:attribute name="id">
    <xsl:value-of select="'cell_'"/>
    <xsl:value-of select="$col"/>
    <xsl:value-of select="'-'"/>
    <xsl:value-of select="$row"/>
  </xsl:attribute>  
  </td>  
</xsl:template>

<xsl:template name="board_row">
  <xsl:param name="cols" select="1"/>
  <xsl:param name="rows" select="1"/>  
  <xsl:param name="row" select="1"/>
  <xsl:param name="col" select="1"/>
  
  <xsl:call-template name="cell">
    <xsl:with-param name="row" select="50 - $row"/>
    <xsl:with-param name="col" select="$col"/>
  </xsl:call-template>

  <xsl:if test="$col &lt; $cols">
    <xsl:call-template name="board_row">
      <xsl:with-param name="cols" select="$cols"/>
      <xsl:with-param name="rows" select="$rows"/>
      <xsl:with-param name="row" select="$row"/>
      <xsl:with-param name="col" select="$col + 1"/>
    </xsl:call-template>
  </xsl:if>
</xsl:template>

<xsl:template name="board_rows">
  <xsl:param name="cols" select="1"/>
  <xsl:param name="rows" select="1"/>  
  <xsl:param name="row" select="1"/>

  <tr>
  <xsl:call-template name="board_row">
    <xsl:with-param name="cols" select="$cols"/>
    <xsl:with-param name="rows" select="$rows"/>
    <xsl:with-param name="row" select="$row"/>
  </xsl:call-template>
  </tr>

  <xsl:if test="$row &lt; $rows">
    <xsl:call-template name="board_rows">
      <xsl:with-param name="cols" select="$cols"/>
      <xsl:with-param name="rows" select="$rows"/>
      <xsl:with-param name="row" select="$row + 1"/>
    </xsl:call-template>
  </xsl:if>
</xsl:template>

<xsl:template name="board">
  <xsl:param name="cols" select="1"/>
  <xsl:param name="rows" select="1"/>

  <table class="board">
  <xsl:call-template name="board_rows">
    <xsl:with-param name="cols" select="$cols"/>
    <xsl:with-param name="rows" select="$rows"/>
  </xsl:call-template>
  </table>
  <style>
  	#cell_5-25 {
  		background-color:black;
  	}
  	#cell_44-25 {
  		background-color:black;
  	}
  	#cell_18-12 {
  		background-color:black;
  	}
  	#cell_31-12 {
  		background-color:black;
  	}
  	#cell_18-38 {
  		background-color:black;
  	}
  	#cell_31-38 {
  		background-color:black;
  	}
  </style>
</xsl:template>

</xsl:stylesheet>
