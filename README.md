# OnlyOneOnlyBird
* Kotlin実装のルーレットアプリ
* 設定された名称のリストから１名を抽選できます
![画面](./view.png)

## Hwo to use
* gradleのインストールは不要
* アプリケーション内部にgradleコマンドを内包しているため

#### Windows
* Windowsの場合はgradlew.batを使う

```bash
clone https://github.com/yasuBrosK/OnlyOneOnlyBird.git
cd OnlyOneOnlyBird
./gradlew.bat build
./gradlew.bat run
```

#### Linux/OSX

```bash
clone https://github.com/yasuBrosK/OnlyOneOnlyBird.git
cd OnlyOneOnlyBird
./gradlew build
./gradlew run
```

## Setting
* resouces/setting/name-list.txtファイルにある名簿が抽選対象者
