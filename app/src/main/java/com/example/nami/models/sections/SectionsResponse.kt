package com.example.nami.models.sections

data class SectionsResponse(
    val actions: List<Action>,
    val legends: List<Legend>,
    val sections: List<Section>,
    val message: String?

)

data class Action(
    val id: Int,
    val name: String,
    val description: String? = null,
    val destructive: Boolean
)

data class Legend(
    val id: Int,
    val name: String,
    val description: String? = null,
    val color: String?=null,
    val visible: Boolean,
    val actions: List<Int>? = null,
    val action: List<Int>? = null
)


data class Section(
    val id: Int,
    val name: String,
    val color: String,
    val legends: List<Int>
)