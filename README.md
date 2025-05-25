# Spring Boot Login JWT App

## 🔧 使用技術
- Spring Boot 3.2
- Spring Security + JWT
- H2 Database（ファイルモード）
- REST API（Postman / Swagger 対応）

## 💡 機能概要
- DB連携ユーザーによるログイン認証
- JWTトークンによる認証・認可
- ロール（ADMIN / USER）制御
- `/auth/login` でトークン取得、`/admin/hello` で認可付きAPI呼び出し

## 🧪 テスト方法
1. `/auth/login` に下記JSONでPOST
```json
{
  "username": "airi",
  "password": "password"
}
```
2. 返されたトークンを /admin/hello にAuthorizationヘッダーで送信
```
Authorization: Bearer <token>
```

## 🔒 ロール制御の詳細
| URL            | ロール          | 内容           |
| -------------- | ------------ | ------------ |
| `/admin/hello` | `ROLE_ADMIN` | 管理者専用メッセージ返却 |

## 🏗️ 今後追加予定
- Reactとの画面連携
- Swagger UI対応
- トークン有効期限＋リフレッシュ