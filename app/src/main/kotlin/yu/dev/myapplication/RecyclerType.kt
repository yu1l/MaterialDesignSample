package yu.dev.myapplication

enum class RecyclerType(val int: Int) {
    HEADER(0),
    FOOTER(1),
    SECTION(2),
    BODY(3);

    companion object {
        // convert Int to Enum
        fun fromInt(int: Int) : RecyclerType {
            return values().firstOrNull { it.int == int } ?: BODY
        }
    }
}