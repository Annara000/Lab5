//35. FilterByPopulation CalcSquarePercent GroupByContinent

package khai.lab.xml

import org.dom4j.Document
import org.dom4j.DocumentHelper
import org.dom4j.Element
import org.dom4j.io.OutputFormat
import java.io.File
import org.dom4j.io.SAXReader
import org.dom4j.io.XMLWriter
import java.io.FileOutputStream

class XMLModifier(file_name: String){

    val title: String
    val where: String

    init {
        //Прочтение xml-файла
        val file = File(file_name)
        val doc = SAXReader().read(file)
        val root = doc.rootElement

        //Парсинг атрибутов корневого элемента
        title = root.attributeValue("title")
        where = root.attributeValue("where")

        //Добавление к корневому элементу нового атрибута
        root.addAttribute("what", "Самые большие города мира")

        //Изменение уже существующего атрибута
        root.addAttribute("where", "Весь мир")

        //Удаление атрибута
        root.remove(root.attribute("title"))

        //Нахождение одного узлового элемента
        val cities_name_node = doc.selectSingleNode("/cities/name")

        //Изменение вложенного текста в элемент
        val city_name = cities_name_node.text
        cities_name_node.text = "Название города"

        //Нахождение нескольких узловых элементов
        val cities_nodes = doc.selectNodes("/cities/city").map {
            println((it as Element).attributeValue("name"))
            println(it.text)
            //it.text = "Упс! Удалились описание городов"
        }

        //Добавление новых элементов
        // Способ 1
        val new_element = DocumentHelper.createElement("countries")
        new_element.text = "Страны"
        root.add(new_element)
        //Способ 2
        root.addElement("continents")


        fun filter_by_population(doc: Document) {
            val cities_nodes2 = doc.selectNodes("/cities/city").map {
                if ((it as Element).attributeValue("population").toInt()>=20000000) {
                    println((it as Element).attributeValue("name"))
                    println("Население: "+(it as Element).attributeValue("population"))
                    println(it.text)
                }
                else if ((it as Element).attributeValue("population").toInt()<20000000) {
                it.remove(it.attribute("city"))
                }
            }
        }

        //Применение функций, задекларированных в интерфейсах к документу
        with(doc) {
            //some_fun(this)
            filter_by_population(this)
        }

        //Запись нового xml-файла
        val writer = XMLWriter(FileOutputStream("./src/main/resources/lab_remastered.xml"),
                OutputFormat.createPrettyPrint())
        writer.write(doc)
        writer.close()
    }

}

fun main(args: Array<String>) {
    val reader = XMLModifier("./src/main/resources/lab.xml")
}