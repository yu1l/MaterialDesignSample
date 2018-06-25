package yu.dev.myapplication

class RecyclerState() {
    constructor(type: RecyclerType, text: String): this() {
        this.type = type
        this.text = text
    }

    // RecyclerAdapterにて追加するレコードのタイプ
    var type: RecyclerType = RecyclerType.BODY
    var text: String = ""
}