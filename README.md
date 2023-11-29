## 本项目实现的最终作用是基于JSP实现的一个旅游网站
## 分为2个角色
### 第1个角色为管理员角色，实现了如下功能：
 - 宾馆信息管理
 - 宾馆预定管理
 - 用户信息管理
 - 留言管理
 - 管理员登陆
 - 管理员管理
 - 网站公告管理
 - 酒店和旅行社管理
 - 预定信息管理
### 第2个角色为用户角色，实现了如下功能：
 - 个人中心管理
 - 宾馆预定
 - 宾馆预订管理
 - 旅行社查看
 - 景点介绍
 - 用户注册
 - 用户登陆
 - 留言板
 - 酒店信息查看
## 数据库设计如下：
# 数据库设计文档

**数据库名：** lvyousite

**文档版本：** 


| 表名                  | 说明       |
| :---: | :---: |
| [admin](#admin) | 管理员表 |
| [adminlog](#adminlog) |  |
| [affiche](#affiche) |  |
| [friendlink](#friendlink) |  |
| [guestbook](#guestbook) |  |
| [guestvisit](#guestvisit) |  |
| [member](#member) | 会员表 |
| [news](#news) | 新闻资讯表 |
| [pmember](#pmember) |  |
| [prep](#prep) |  |
| [replay](#replay) |  |
| [sale](#sale) |  |
| [system](#system) |  |

**表名：** <a id="admin">admin</a>

**说明：** 管理员表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  N   |       |   |
|  2   | username |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 用户名  |
|  3   | password |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 密码  |
|  4   | creattime |   datetime   | 19 |   0    |    Y     |  N   |   NULL    |   |
|  5   | flag |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  6   | isuse |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  7   | logintimes |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  8   | quanxian |   varchar   | 1000 |   0    |    Y     |  N   |   NULL    |   |

**表名：** <a id="adminlog">adminlog</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  N   |       |   |
|  2   | username |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 用户名  |
|  3   | password |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 密码  |
|  4   | logintime |   datetime   | 19 |   0    |    Y     |  N   |   NULL    |   |
|  5   | loginip |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  6   | useros |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  7   | ok |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |

**表名：** <a id="affiche">affiche</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  N   |       |   |
|  2   | title |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  3   | content |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 内容  |
|  4   | addtime |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 添加时间  |
|  5   | adder |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  6   | ifhide |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |

**表名：** <a id="friendlink">friendlink</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  N   |       |   |
|  2   | linkname |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  3   | linkurl |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  4   | linkpic |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  5   | intero |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  6   | ifhide |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  7   | ordervalue |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  8   | addTime |   datetime   | 19 |   0    |    Y     |  N   |   NULL    | 添加时间  |

**表名：** <a id="guestbook">guestbook</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  N   |       |   |
|  2   | nickname |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  3   | pic |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 图片  |
|  4   | email |   varchar   | 45 |   0    |    Y     |  N   |   NULL    | 邮箱  |
|  5   | QQ |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | QQ号码  |
|  6   | weburl |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  7   | blogurl |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  8   | expressions |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  9   | content |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 内容  |
|  10   | addTime |   datetime   | 19 |   0    |    Y     |  N   |   NULL    | 添加时间  |
|  11   | ip |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | IP地址  |
|  12   | replay |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  13   | ifhide |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |

**表名：** <a id="guestvisit">guestvisit</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  N   |       |   |
|  2   | ip |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | IP地址  |
|  3   | vtime |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |

**表名：** <a id="member">member</a>

**说明：** 会员表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  N   |       |   |
|  2   | username |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 用户名  |
|  3   | password |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 密码  |
|  4   | TYPE |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 类型  |
|  5   | regtime |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  6   | ifuse |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  7   | logintimes |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  8   | lasttime |   datetime   | 19 |   0    |    Y     |  N   |   NULL    |   |
|  9   | lastip |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |

**表名：** <a id="news">news</a>

**说明：** 新闻资讯表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  N   |       |   |
|  2   | title |   varchar   | 150 |   0    |    Y     |  N   |   NULL    |   |
|  3   | fenlei |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  4   | content |   text   | 65535 |   0    |    Y     |  N   |   NULL    | 内容  |
|  5   | addtime |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 添加时间  |
|  6   | adder |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  7   | visit |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |

**表名：** <a id="pmember">pmember</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  N   |       |   |
|  2   | mid |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  3   | realname |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 真实名字  |
|  4   | sex |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 性别  |
|  5   | bir |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  6   | sheng |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  7   | city |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 城市名称  |
|  8   | telphone |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  9   | email |   varchar   | 45 |   0    |    Y     |  N   |   NULL    | 邮箱  |
|  10   | question |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  11   | answer |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  12   | ADDRESS |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 地址  |

**表名：** <a id="prep">prep</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  N   |       |   |
|  2   | title |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 标题  |
|  3   | rs |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  4   | sj |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  5   | ts |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  6   | lxr |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 联系人  |
|  7   | lxfs |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  8   | addtime |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 添加时间  |
|  9   | member |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |

**表名：** <a id="replay">replay</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  N   |       |   |
|  2   | mid |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  3   | replay |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  4   | replayer |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  5   | replaytime |   datetime   | 19 |   0    |    Y     |  N   |   NULL    |   |

**表名：** <a id="sale">sale</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  N   |       |   |
|  2   | title |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 标题  |
|  3   | url |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 网络地址  |
|  4   | dz |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  5   | yb |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  6   | dh |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  7   | jd |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  8   | content |   text   | 65535 |   0    |    Y     |  N   |   NULL    | 内容  |
|  9   | addtime |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 添加时间  |

**表名：** <a id="system">system</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  N   |       |   |
|  2   | sitename |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  3   | url |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 网络地址  |
|  4   | keyword |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  5   | description |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 描述  |
|  6   | email |   varchar   | 45 |   0    |    Y     |  N   |   NULL    | 邮箱  |
|  7   | state |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 状态  |
|  8   | reasons |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  9   | dir |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  10   | record |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  11   | copyright |   text   | 65535 |   0    |    Y     |  N   |   NULL    |   |

**运行不出来可以微信 javape 我的公众号：源码码头**
