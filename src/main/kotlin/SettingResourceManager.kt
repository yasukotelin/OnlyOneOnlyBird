import java.io.File

/**
 * 名簿管理オブジェクト
 */
object SettingResourceManager {

    /**
     * 名簿読み込み処理
     * @return 名簿リスト
     **/
    fun nameListLoad(): List<String> = File(javaClass.getResource("setting/name-list.txt").toURI()).readLines()

    fun nameListSave(listString: String) = File(javaClass.getResource("setting/name-list.txt").toURI()).writeText(listString)

    /**
     * OnlyOneモードの名称読み込み処理
     * @return OnlyOne名称
     */
    fun onlyOneNameLoad(): String = File(javaClass.getResource("setting/only-one-name.txt").toURI()).readText()

    /**
     * OnlyOneモードの名称書き込み処理
     */
    fun onlyOneNameSave(name: String) = File(javaClass.getResource("setting/only-one-name.txt").toURI()).writeText(name)
}