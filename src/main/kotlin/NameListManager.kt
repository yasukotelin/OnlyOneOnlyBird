import java.io.File

/**
 * 名簿管理オブジェクト
 */
object NameListManager {

    /**
     * 名簿読み込み処理
     * @return 名簿リスト
     **/
    fun load(): List<String> = File(javaClass.getResource("setting/name-list.txt").toURI()).readLines()
}