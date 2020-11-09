package io.legopiraat.markdownmagic.element

sealed trait ListType
case object Numeric extends ListType
case object Dotted  extends ListType
