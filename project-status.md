# 献立生成アプリ 開発状況

## プロジェクト概要
ChatGPT APIを活用した献立・買い物リスト自動生成アプリ

## 技術スタック
### Backend
- 言語: Java 17
- フレームワーク: Spring Boot 3.5.7
- ビルドツール: Gradle (Kotlin DSL)
- パッケージ名: com.github.yuta600.kondateai

### Frontend
- フレームワーク: React + Vite
- 状態: 入力フォーム作成済み

### DB
- MySQL (MVP後に実装予定)

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

## MVP仕様
- 入力: 家族構成、予算、地域、スーパー名
- 処理: ChatGPT APIで**複数日分の献立**生成
- 出力:
    - 各日の献立(タイトル、材料、材料費)
    - 統合買い物リスト
    - 合計金額
- Phase 2: 調理手順(レシピURL)、買い物リスト統合

## 現在のプロジェクト構成
```
com.github.yuta600.kondateai/
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

## 次のタスク
- [ ] フロントエンドとの接続
- [ ] CORS設定
- [ ] エラーハンドリング強化
    - API呼び出し失敗時の処理
    - タイムアウト処理
    - わかりやすいエラーメッセージ
- [ ] ログ出力の整備
- [ ] 本番環境用の設定

## 学んだこと
- インターフェースによる設計の重要性
- 依存性注入（DI）の実践
- WebClientでのHTTP通信
- JSONパース処理
- プロンプトエンジニアリングの基礎
- 段階的開発（ダミー実装→本番実装）の効果

## 注意事項
- Gemini APIキー: application-local.propertiesに保存（Git管理外）
- APIモデル: gemini-2.5-flash
- 予算単位: フロント入力は万単位、API送信時に円換算
- Gemini APIレスポンス: マークダウン記法除去処理を実装済み