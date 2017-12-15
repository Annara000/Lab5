package khai.lab.xml

import org.dom4j.Document
import org.dom4j.Element

/**
 * Отфильтровать города (cities) по количеству населения и убрать несоответсующие города из документа
 */
interface FilterByPopulation {
    fun filter_by_population(doc: Document)

}

/**
 * Рассчитать процентную долю площади (среди всех) для городов и сохранить в документе значения в виде атрибутов
 */
interface CalcSquarePercent {
    fun calc_square_percent(doc: Document)
}

/**
 * Сгруппировать все города по континентам и собрать в документе города в отдельные группы, создав элементы continent
 */
interface GroupByContinent {
    fun group_by_continent(doc: Document)
}