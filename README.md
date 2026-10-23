# ZhuaTech ContentAI


## 本次升级：内容发布素材权利与复核期限

核对稿件素材授权齐套、权利到期时间和审阅状态；存在未授权素材不建议发布。只读统计，不发送内容到任何平台。

新增已认证的结构化录入工作台（`/operations.html`），支持批量核算、逐条结果依据、分级复核和CSV/完整JSON导出。结果由真实后端算法计算，不是预置演示数字。所有数值精确传递，不调用外部模型。

这是**只读核算**：不写库、不审批、不自动执行财务、签署、销毁或设备控制。输入条件满足不是执行授权。[算法、输入边界与接口说明](docs/OPERATIONS-REVIEW.md)。
## 本次功能更新：内容发布文字校验

逐字校验必备表述、禁用词位置及长度限制，输出可定位问题清单；规则匹配不等同语义审核或自动发布。 登录后从「业务计算工具」进入，支持真实后端校验与结果导出。详见[接口、示例与边界说明](docs/AI_BUSINESS_UPGRADE_20261023.md)。本次不改变既有许可及联系方式。

[简体中文](README.md) | [English](README.en.md)

## 企业智能内容生产与合规平台

创作效率、品牌一致性和内容安全不应彼此牺牲。ZhuaTech ContentAI 把企业知识、生成式 AI、素材版权和多级审核连接为可管理的内容生产线。

[官网](https://www.zhuatech.cn/)　[部署](deploy/README.md)　[接口](docs/api.md)　[授权](LICENSE)

![企业智能内容运营中心](docs/images/contentai-operations-dashboard.png)

### 一套可审计的内容工厂

- 从创意简报生成多版本内容草稿
- 绑定产品事实、品牌术语和素材来源
- 执行事实、禁用词、隐私、版权和相似度检查
- 支持编辑、品牌、法务多角色审核
- 面向官网、公众号、社媒、邮件等渠道自动适配
- 跟踪生产周期、审核通过率和渠道效果

![内容运营移动创作工作台](docs/images/contentai-creator-h5.png)

发布门禁现已覆盖个人信息、企业禁用词和事实引用完整性。审核接口会给出 `PASS`、`REVIEW` 或 `BLOCK` 结论、量化风险分以及逐项整改原因，适合嵌入编辑器保存、渠道发布和法务复核流程。

### 技术实现

```text
Vue 3 管理端/H5 → Spring Boot API → 业务流程与审计 → MySQL
                                      ↘ AiProvider / 内容生成服务
```

后端基于 Java 21、Spring Security、JWT、JPA、Flyway，包名为 `cn.zhuatech.contentai`；前端采用 Vue 3、Pinia、Vue Router、Axios 与 Vite。演示 AI Provider 不联网、不保存真实密钥，可由实施方接入企业批准的模型服务。

```bash
cd frontend && npm install && npm run dev:demo
```

访问 `http://localhost:5173`。`planner / Demo@2026` 进入内容运营中心，`operator / Demo@2026` 进入创作工作台。演示品牌、内容、素材和指标均为虚构数据。

### 使用许可和商业合作

本工程由上海如静知华信息科技有限公司发布，仅限个人学习、研究及非商业技术交流，**不得商用**。企业部署、生产使用、SaaS、项目交付、收费培训、品牌替换、商业再发行等行为必须取得我方书面授权，详见 [LICENSE](LICENSE)。

深度定制、私有化部署与商业授权，请联系[知华科技](https://www.zhuatech.cn/)：

| 微信咨询 | 微信咨询 |
| --- | --- |
| ![知华科技微信咨询二维码一](docs/images/zhuatech-wechat-consulting.png) | ![知华科技微信咨询二维码二](docs/images/zhuatech-wechat-consulting-2.png) |

SEO 关键词：AI 内容生成系统源码、企业内容中台、品牌内容审核、生成式 AI 平台、Java ContentAI、Vue 内容管理、知华科技。

## 内容事实依据质量

新增 `POST /api/contentai/insights/grounding-quality`，检查事实主张引用、可信来源、来源矛盾和 AI 内容披露，输出 `PUBLISH`、`REVIEW` 或 `BLOCK`。

## 多渠道内容发布治理

`POST /api/enterprise/contentai/publication-governance` 在发布前计算事实主张证据覆盖率，并校验禁止性宣传、品牌、版权、个人信息、未成年人保护、人工审批和 AI 内容披露，输出 `PUBLISH / REVIEW / BLOCKED`。
