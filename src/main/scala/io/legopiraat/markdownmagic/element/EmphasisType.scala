package io.legopiraat.markdownmagic.element

sealed trait EmphasisType
case object Clear         extends EmphasisType
case object Bold          extends EmphasisType
case object Italic        extends EmphasisType
case object Underlined    extends EmphasisType
case object Strikethrough extends EmphasisType
