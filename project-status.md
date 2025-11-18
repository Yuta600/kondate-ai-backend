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
- ✅ **献立生成API - 入力受付機能実装**
    - MenuRequest DTO作成(家族構成、予算、郵便番号、スーパー名)
    - MenuController作成(@RestController, POST /api/menu/generate)
    - バリデーション実装(@Min, @Max, @NotBlank + カスタムチェック)
    - 例外ハンドリング実装(BadRequestException + GlobalExceptionHandler)
    - ErrorResponse DTO作成
    - Postmanでテスト確認済み
- ✅ MenuResponse(出力DTO)作成
    - 複数献立対応(DailyMenuのネスト構造)
    - 買い物リスト機能
    - 将来拡張を考慮した設計(recipeUrl)
- ✅ コントローラー修正
    - ダミーデータで3日分の献立生成
    - 動作確認完了(Postman)

## MVP仕様
- 入力: 家族構成、予算、地域、スーパー名
- 処理: ChatGPT APIで**複数日分の献立**生成
- 出力:
    - 各日の献立(タイトル、材料、材料費)
    - 統合買い物リスト
    - 合計金額
- Phase 2: 調理手順(レシピURL)、買い物リスト統合

## 次のタスク
- [ ] OpenAI APIキー取得
- [ ] AiServiceインターフェース作成
- [ ] ChatGPT API連携実装
- [ ] (オプション) CORS設定・フロント連携

## 注意事項
- OpenAI APIキー: 未取得(今後必要)
- CORS設定: フロント連携時に必要