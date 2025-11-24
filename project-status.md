# 献立生成アプリ - バックエンド開発状況

## プロジェクト概要
Gemini APIを活用した献立・買い物リスト自動生成アプリのバックエンド

## 技術スタック
- 言語: Java 17
- フレームワーク: Spring Boot 3.5.7
- ビルドツール: Gradle (Kotlin DSL)
- API: Google Gemini API (gemini-2.5-flash)
- パッケージ名: com.github.yuta600.kondateai

## 完了済みタスク
- ✅ Java 17インストール
- ✅ SpringBootプロジェクトセットアップ
- ✅ パッケージ名修正 (Yuta600 → yuta600, KondateAI → kondateai)
- ✅ Spring Security無効化設定
- ✅ GitHubリポジトリ作成・初回プッシュ
    - リポジトリ: kondate-ai-backend (Public)
- ✅ IntelliJ開発環境設定
    - 自動ビルド有効化
    - 自動インポート設定
- ✅ DTOクラス実装
    - MenuRequest（入力DTO、バリデーション付き）
    - MenuResponse（出力DTO、ネスト構造）
- ✅ エラーハンドリング実装
    - BadRequestException
    - GlobalExceptionHandler
- ✅ AI連携機能実装
    - AiServiceインターフェース設計
    - DummyAiService実装（テスト用）
    - GeminiAiService実装（本番用）
    - 依存性注入による切り替え機能
    - WebClientによるHTTP通信
    - JSONレスポンスのパース処理
    - マークダウン記法除去対応
- ✅ プロンプトエンジニアリング
    - 3日分の献立生成
    - 量付き買い物リスト生成
- ✅ Gemini API設定
    - APIキー取得
    - application-local.properties設定
    - .gitignoreによる秘密情報保護
- ✅ CORS設定
    - WebConfig実装
    - localhost:5173からのアクセス許可

## MVP仕様（達成✅）
### 入力（MenuRequest）
- adults: 大人の人数（0-10人）
- children: 子供の人数（0-10人）
- postalCode: 郵便番号（7桁）
- supermarketName: スーパー名
- budget: 予算（万円単位、double型）

### 処理
- Gemini APIで3日分の献立生成
- 地域とスーパーを考慮した価格設定
- 材料の統合と買い物リスト生成

### 出力（MenuResponse）
- menus: 3日分の献立
    - day: 曜日
    - title: 献立名
    - ingredients: 材料リスト（名前・分量）
    - cost: 推定費用
- shoppingList: 統合された買い物リスト
- totalCost: 合計金額

## プロジェクト構成
```
com.github.yuta600.kondateai/
├── config/
│   ├── SecurityConfig.java
│   └── WebConfig.java
├── controller/
│   └── MenuController.java
├── dto/
│   ├── request/
│   │   └── MenuRequest.java
│   └── response/
│       └── MenuResponse.java
├── exception/
│   ├── BadRequestException.java
│   ├── ErrorResponse.java
│   └── GlobalExceptionHandler.java
├── service/
│   ├── AiService.java          # インターフェース
│   ├── DummyAiService.java     # ダミー実装
│   └── GeminiAiService.java    # Gemini実装（@Primary）
└── KondateAiApplication.java
```

## 次のタスク（Phase 2）
- [ ] エラーハンドリング強化
    - より詳細なエラーメッセージ
    - タイムアウト処理
    - リトライ機能
- [ ] ログ出力の整備
    - リクエスト/レスポンスのログ
    - エラーログの詳細化
- [ ] 本番環境用の設定
    - プロファイル分離（dev/prod）
    - 環境変数管理
- [ ] データベース実装
    - ユーザー管理
    - 献立履歴保存
    - お気に入り機能
- [ ] テストコード
    - 単体テスト
    - 統合テスト
- [ ] API仕様書
    - Swagger/OpenAPI導入

## 学んだこと
- インターフェースによる設計の重要性
- 依存性注入（DI）の実践
- WebClientでのHTTP通信
- JSONパース処理
- プロンプトエンジニアリングの基礎
- 段階的開発（ダミー実装→本番実装）の効果
- CORS（Cross-Origin Resource Sharing）

## 注意事項
- Gemini APIキー: application-local.propertiesに保存（Git管理外）
- APIモデル: gemini-2.5-flash
- 予算単位: フロントから万単位で受け取り、内部で円換算
- Gemini APIレスポンス: マークダウン記法除去処理を実装済み
- サーバーポート: 8080

## 関連リポジトリ
- フロントエンド: https://github.com/YOUR_USERNAME/kondate-ai-frontend