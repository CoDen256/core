package io.github.coden256.notion.internals

import notion.api.v1.NotionClient
import notion.api.v1.model.common.FormulaType
import notion.api.v1.model.common.ObjectType
import notion.api.v1.model.common.PropertyType
import notion.api.v1.model.databases.DatabaseProperty
import notion.api.v1.model.pages.PageParent
import notion.api.v1.model.pages.PageParentType
import notion.api.v1.model.pages.PageProperty
import notion.api.v1.model.search.DatabaseSearchResult

interface Notion {
    fun database(name: String): Database
    fun add(database: String, page: Page): Unit
}

class NotionImpl(private val client: NotionClient) : Notion {
    override fun database(name: String): Database {
        return databaseSearchResult(name)
            .let { db ->
                db.title?.firstOrNull()?.plainText?.let { Database(it, db.getPages()) }
            } ?: throw Exception("Database could not be instantiated: $name")
    }

    private fun databaseSearchResult(name: String): DatabaseSearchResult = client.search(name)
        .results
        .filter { it.objectType == ObjectType.Database }
        .map { it.asDatabase() }
        .firstOrNull { it.title?.firstOrNull() { it.plainText == name } != null }
        ?: throw Exception("Database not found: $name")

    private fun DatabaseSearchResult.getPages(): List<Page> {
        return client
            .queryDatabase(id)
            .results
            .map { p ->
                Page(p.properties.values.first { it.id =="title" }
                    .title?.firstOrNull()?.plainText?: "", p.properties() )
            }
    }

    private fun notion.api.v1.model.pages.Page.properties(): Map<String, Property> {
        return properties
            .filterNot {  it.value.id == "title" || it.value.type == null}
            .mapValues { it.value.map() }


    }

    private fun PageProperty.map(): Property{
        return when(type!!){
            PropertyType.RichText -> RichText(richText?.firstOrNull()?.plainText?: "")
            PropertyType.MultiSelect -> MultiSelect(multiSelect?.mapNotNull { it.name } ?: listOf())
            PropertyType.Number -> Number(number ?: 0)
            PropertyType.Select -> Select(select?.name ?: "")
            PropertyType.Date -> Date(date)
            PropertyType.Formula -> Formula(formula)
            PropertyType.Relation -> Relation(relation)
            PropertyType.Rollup -> Rollup(rollup)
            PropertyType.Title -> Title(title)
            PropertyType.People -> People(people)
            PropertyType.Files -> Files(files)
            PropertyType.Checkbox -> Checkbox(checkbox)
            PropertyType.Url -> Url(url)
            PropertyType.Email -> Email(email)
            PropertyType.PhoneNumber -> PhoneNumber(phoneNumber)
            PropertyType.CreatedTime -> CreatedTime(createdTime)
            PropertyType.CreatedBy -> CreatedBy(createdBy)
            PropertyType.LastEditedTime -> LastEditedTime(lastEditedTime)
            PropertyType.LastEditedBy -> LastEditedBy(lastEditedBy)
            PropertyType.UniqueId -> UniqueId(uniqueId)
            PropertyType.PropertyItem -> PropertyItem(this)
        }
    }

    private fun Property.map(): PageProperty = PageProperty().apply{
        when(val property = this@map){
            is RichText -> richText = listOf(PageProperty.RichText(plainText = property.text))
            is MultiSelect -> multiSelect = listOf()
            is Number -> number = property.value
            is Select -> select = DatabaseProperty.Select.Option(name = property.value)
            is Date -> date = PageProperty.Date()
            is Formula -> formula = PageProperty.Formula(type = FormulaType.StringType, string = property.value as String)
            is Relation -> relation = listOf()
            is Rollup -> rollup = PageProperty.Rollup(type = "")
            is Title -> title = listOf(PageProperty.RichText(plainText = property.value as String))
            is People -> people = listOf()
            is Files -> files = listOf()
            is Checkbox -> checkbox = true
            is Url -> url = ""
            is Email -> email = ""
            is PhoneNumber -> phoneNumber = ""
            is CreatedTime -> {}
            is CreatedBy -> {}
            is LastEditedTime -> {}
            is LastEditedBy -> {}
            is UniqueId -> {}
            is PropertyItem -> {}
        }
    }

    override fun add(database: String, page: Page) {
        val re = databaseSearchResult(database)
        client.createPage(PageParent(PageParentType.DatabaseId, databaseId = re.id),
            mapOf("Description" to PageProperty(
                richText = listOf(PageProperty.RichText(text = PageProperty.RichText.Text("hello"))))))
    }
}

sealed interface Property {
}

data class MultiSelect(val options: List<String>) : Property
data class RichText(val text: String) : Property
data class Number(val value: kotlin.Number): Property
data class Select(val value: String): Property
data class Date(val value: Any?): Property
data class Formula(val value: Any?): Property
data class Relation(val value: Any?): Property
data class Rollup(val value: Any?): Property
data class Title(val value: Any?): Property
data class People(val value: Any?): Property
data class Files(val value: Any?): Property
data class Checkbox(val value: Any?): Property
data class Url(val value: Any?): Property
data class Email(val value: Any?): Property
data class PhoneNumber(val value: Any?): Property
data class CreatedTime(val value: Any?): Property
data class CreatedBy(val value: Any?): Property
data class LastEditedTime(val value: Any?): Property
data class LastEditedBy(val value: Any?): Property
data class UniqueId(val value: Any?): Property
data class PropertyItem(val value: Any?): Property

data class Page(val name: String, val properties: Map<String, Property>)
data class Database(val name: String, val pages: List<Page>)


fun main() {
    val notionClient = NotionClient("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")
    val c = NotionImpl(notionClient)
    c.database("alpha")
    c.add("alpha", Page("s", mapOf()))
    notionClient.clientId
}