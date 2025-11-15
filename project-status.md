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

## MVP仕様
- 入力: 家族構成、予算、地域、スーパー名
- 処理: ChatGPT APIで献立生成
- 出力: 献立タイトル、材料、材料費、買い物メモ
- DB・認証機能は後回し

## 次のタスク
- [ ] フロントからのリクエストを受け取るコントローラー作成
- [ ] ChatGPT API連携
- [ ] レスポンスデータ整形

## 注意事項
- OpenAI APIキー: 未取得(今後必要)
- CORS設定: フロント連携時に必要