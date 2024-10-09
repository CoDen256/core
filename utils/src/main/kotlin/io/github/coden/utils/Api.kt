import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class QueryParam(val value: String)
interface QueryParamEnumType { val param: String }


fun parseQueryParams(request: Any, compile: (Any?) -> String = {toString(it)}): Map<String, String> {
    val kClass = request::class
    if (!kClass.isData) return emptyMap()
    val map = kClass.memberProperties
        .associate { (it.findAnnotation<QueryParam>()?.value ?: it.name) to it.call(request) }
        .filterValues { it != null }
        .mapValues { compile(it.value) }
    return map
}

fun toString(obj: Any?): String {
    if (obj is QueryParamEnumType) return obj.param
    if (obj is Collection<*>)  return obj.joinToString(",") { toString(it) }
    if (obj == null) return ""
    return obj.toString()
}
