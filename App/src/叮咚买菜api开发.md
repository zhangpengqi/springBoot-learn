<h3>叮咚买菜api开发</h4>

<h5>使⽤SpringBoot框架 + Postman，完成图书管理API开发，API开发完成后，使⽤React完成前端功能。
统⼀响应格式:

```json
{"code": "SUCCESS", "message": null, "data": null}
```

<h5>code 为SUCCESS 代表请求成功， data 中存放具体的业务数据。</h5>

```json
{"code": "ERROR", "message": "失败原因", "data": null}
```

<h5>code 不为SUCCESS 代表请求失败， message 中是失败原因。</h5>



## 1. 轮播图信息   <font color=gree>完成</font>

| API              | /api/banner                                            |
| ---------------- | ------------------------------------------------------ |
| 请求方式         | GET                                                    |
| Content-Type     | application/x-www-form-urlencoded                      |
| 请求参数(Body)： |                                                        |
|                  |                                                        |
| 请求结果         |                                                        |
| 失败             | {"code": "ERROR", "message": "失败原因", "data": null} |

成功返回数据如下：

```json
{
    "code": "SUCCESS",
    "message": null,
    "data": {
        "bannerList": [
            {
                "id": 1,
                "theme": "小龙虾",
                "url": "123"
            },
            {
                "id": 2,
                "theme": "冲会员",
                "url": "123"
            },
            {
                "id": 3,
                "theme": "送红包",
                "url": "123"
            }
        ]
    }
}
```

## 2. 导航框信息   <font color=gree>完成</font>

| API              | /api/nav                                               |
| ---------------- | ------------------------------------------------------ |
| 请求方式         | GET                                                    |
| Content-Type     | application/x-www-form-urlencoded                      |
| 请求参数(Body)： |                                                        |
| limit:           | 查询信息条数                                           |
| 请求结果         |                                                        |
| 失败             | {"code": "ERROR", "message": "失败原因", "data": null} |

成功返回数据如下：

```json
{
    "code": "SUCCESS",
    "message": null,
    "data": {
        "navList": [
            {
                "id": 1,
                "name": "蔬菜豆制品",
                "url": "123"
            },
            {
                "id": 2,
                "name": "肉禽蛋",
                "url": "123"
            },
            {
                "id": 3,
                "name": "水产海鲜",
                "url": "123"
            }
        ]
    }
}
```

## 2. 1  导航框信息(limit=10)   <font color=gree>完成</font>

| API              | /api/navhome                                           |
| ---------------- | ------------------------------------------------------ |
| 请求方式         | GET                                                    |
| Content-Type     | application/x-www-form-urlencoded                      |
| 请求参数(Body)： |                                                        |
| 请求结果         |                                                        |
| 失败             | {"code": "ERROR", "message": "失败原因", "data": null} |

成功返回数据如下：

```json
{
    "code": "SUCCESS",
    "message": null,
    "data": {
        "navList": [
            {
                "id": 1,
                "name": "蔬菜豆制品",
                "url": "123"
            },
            {
                "id": 2,
                "name": "肉禽蛋",
                "url": "123"
            },
            {
                "id": 3,
                "name": "水产海鲜",
                "url": "123"
            }
        ]
    }
}
```



## 3. 所有菜品信息   <font color=gree>完成</font>

| API              | /api/products/all                                      |
| ---------------- | ------------------------------------------------------ |
| 请求方式         | GET                                                    |
| Content-Type     | application/x-www-form-urlencoded                      |
| 请求参数(Body)： |                                                        |
|                  |                                                        |
| 请求结果         |                                                        |
| 失败             | {"code": "ERROR", "message": "失败原因", "data": null} |

成功返回数据如下：

```json
{
	"code":"SUCCESS",
	"message":null,
	"data":{
		"productList":[
			{
    			"id": 1,
        		"name": "土豆",
        		"price":2.50,
                "url":"http://e.hiphotos.baidu.com/2072e34349b033bba23.jpg"
    		},
    		{
        		"id": 2,
        		"name": "鸡蛋",
        		"price":3.00,
                "url":"http://e.hiphotos.baidu.com/2072e34349b033bba23.jpg"
    		}
		]
	}
}
```

## 4. 单个菜品信息   <font color=gree>完成</font>

| API              | /api/products/{productId}                              |
| ---------------- | ------------------------------------------------------ |
| 请求方式         | GET                                                    |
| Content-Type     |                                                        |
| 请求参数(Body)： |                                                        |
|                  |                                                        |
| 请求结果:        |                                                        |
| 失败             | {"code": "ERROR", "message": "失败原因", "data": null} |

成功返回数据如下：

```json
{
	"code":"SUCCESS",
	"message":null,
	"data":{
            "id": 1,
            "name": "蔬菜豆制品",
            "discribe":"受季节性关照及气温影响",
            "price":2.50,
            "url":"http://e.hiphotos.baidu.com/2072e34349b033bba23.jpg",
            "weight":"500g",
            "condition":"冷藏",
            "expiry_date":"9天",
            "product_price":"http://e.hiphotos.baidu.com/2072e34349b033bba23.jpg",
	}
}
```

## 5. 按名称搜索菜品信息   <font color=gree>完成</font>

| API              | /api/products/searchByName                             |
| ---------------- | ------------------------------------------------------ |
| 请求方式         | get                                                    |
| Content-Type     | application/x-www-form-urlencoded                      |
| 请求参数(Body)： |                                                        |
| name:            | 蔬菜名                                                 |
| 请求结果:        |                                                        |
| 失败             | {"code": "ERROR", "message": "失败原因", "data": null} |

成功返回数据如下：

```json
{
	"code":"SUCCESS",
	"message":null,
	"data":{
		"nav":[
			{
                "id": 2,
        		"name": "鸡蛋",
        		"price":3.50,
                "url":"http://e.hiphotos.baidu.com/2072e34349b033bba23.jpg"
    		},
    		{
        		"id": 2,
        		"name": "鸡肉",
                "price":3.50,
        		"url":"http://e.hiphotos.baidu.com/2072e34349b033bba23.jpg"
    		}
		]
	}
}
```

## 6. 按照导航id和分类明细detail_id,查询所有商品

| API              | /api/products/detailsearch                             |
| ---------------- | ------------------------------------------------------ |
| 请求方式         | POST                                                   |
| Content-Type     | application/x-www-form-urlencoded                      |
| 请求参数(Body)： |                                                        |
| id:              | 导航id                                                 |
| detail_id        | 详情分类id（分类顶部的导航栏）                         |
| 请求结果:        |                                                        |
| 失败             | {"code": "ERROR", "message": "失败原因", "data": null} |

成功返回数据如下：

```json
{
	"code":"SUCCESS",
	"message":null,
	"data":{
		"nav":[
			{
                "id": 2,
        		"name": "鸡蛋",
        		"price":3.50,
                "url":"http://e.hiphotos.baidu.com/2072e34349b033bba23.jpg"
    		},
    		{
        		"id": 2,
        		"name": "鸡肉",
                "price":3.50,
        		"url":"http://e.hiphotos.baidu.com/2072e34349b033bba23.jpg"
    		}
		]
	}
}
```

## 7. 购物车接口

### 7.1 获取购物车信息    <font color=gree>完成</font>

| API              | /shoppingCart/select                                   |
| ---------------- | ------------------------------------------------------ |
| 请求方式         | GET                                                    |
| Content-Type     |                                                        |
| 请求参数(Body)： |                                                        |
| token            | token值                                                |
| 请求结果:        |                                                        |
| 失败             | {"code": "ERROR", "message": "失败原因", "data": null} |

成功返回数据如下：

```json
{
    "code": "SUCCESS",
    "message": null,
    "data": {
        "user_id": 3,
        "product_list": [
            {
                "number": 1,
                "product": {
                    "id": 1,
                    "name": "土豆鸡蛋8枚",
                    "describe": "别看我土里土气，我可是货真价实的高蛋白",
                    "price": 6.90,
                    "navId": 2,
                    "detailId": 3,
                    "url": "url",
                    "status": "1",
                    "stock": 12,
                    "weight": "50g",
                    "condition": "5℃",
                    "expiryDate": "30天"
                }
            },
            {
                "number": 10,
                "product": {
                    "id": 2,
                    "name": "土豆(黄心) 约500g",
                    "describe": "别看我土里土气，我可是货真价实的高蛋白",
                    "price": 6.90,
                    "navId": 2,
                    "detailId": 3,
                    "url": "url",
                    "status": "1",
                    "stock": 12,
                    "weight": "50g",
                    "condition": "5℃",
                    "expiryDate": "30天"
                }
            },
            {
                "number": 1,
                "product": {
                    "id": 3,
                    "name": "香葱50g",
                    "describe": "你负责炒，次啊票颜值交给我",
                    "price": 0.50,
                    "navId": 2,
                    "detailId": 3,
                    "url": "url",
                    "status": "1",
                    "stock": 12,
                    "weight": "50g",
                    "condition": "冷藏",
                    "expiryDate": "3天"
                }
            }
        ]
    }
}
```

### 7.2 向购物车添加商品    <font color=gree>完成</font>

| API              | /shoppingCart/add                                      |
| ---------------- | ------------------------------------------------------ |
| 请求方式         | post                                                   |
| Content-Type     |                                                        |
| 请求参数(Body)： |                                                        |
| token            | token值                                                |
| product_id       | 商品id                                                 |
|                  |                                                        |
| 请求结果:        |                                                        |
| 失败             | {"code": "ERROR", "message": "失败原因", "data": null} |

成功返回数据如下：

```json
{
    "code": "SUCCESS",
    "message": "购物车添加成功",
    "data": null
}
```

### 7.3 更新购物车商品数量    <font color=gree>完成</font>

| API              | /shoppingCart/update                                   |
| ---------------- | ------------------------------------------------------ |
| 请求方式         | post                                                   |
| Content-Type     |                                                        |
| 请求参数(Body)： |                                                        |
| token            | token值                                                |
| product_id       | 商品id                                                 |
| number           | 商品数量                                               |
| 请求结果:        |                                                        |
| 失败             | {"code": "ERROR", "message": "失败原因", "data": null} |

成功返回数据如下：

```json
{
    "code": "SUCCESS",
    "message": "购物车更新成功",
    "data": null
}
```

### 7.4 删除购物车的商品    <font color=gree>完成</font>

| API              | /shoppingCart/delete                                   |
| ---------------- | ------------------------------------------------------ |
| 请求方式         | DELETE                                                 |
| Content-Type     |                                                        |
| 请求参数(Body)： |                                                        |
| token            | token值                                                |
| product_id       | 商品id                                                 |
|                  |                                                        |
| 请求结果:        |                                                        |
| 失败             | {"code": "ERROR", "message": "失败原因", "data": null} |

成功返回数据如下：

```json
{
    "code": "SUCCESS",
    "message": "购物车商品删除成功",
    "data": null
}
```



## <font color=red>8. 订单接口、未完成</font>

| API              | /api/token/order                                       |
| ---------------- | ------------------------------------------------------ |
| 请求方式         | GET                                                    |
| Content-Type     |                                                        |
| 请求参数(Body)： |                                                        |
| token            | token值                                                |
| 请求结果:        |                                                        |
| 失败             | {"code": "ERROR", "message": "失败原因", "data": null} |

成功返回数据如下：

```

```

## 9.登录   <font color=gree>完成</font>

| 登录（创建token） | /token/create                                                |
| ----------------- | ------------------------------------------------------------ |
| 请求方式          | post                                                         |
| Content-Type      |                                                              |
| 请求参数(Body)：  |                                                              |
| mobile            | 手机号                                                       |
| password          | 密码                                                         |
| 请求结果:         |                                                              |
| 失败              | {"code": "ERROR", "message": "失败原因", "data": null}       |
| 成功              | {"code": "SUCCESS", "data": {"token":"xxx",'id'=>'⽤户id'}, "message": null} |

## 10. 获取个人信息   <font color=gree>完成</font>

| 获取个人信息）   | /user/whoami                                           |
| ---------------- | ------------------------------------------------------ |
| 请求方式         | get                                                    |
| Content-Type     |                                                        |
| 请求参数(Body)： |                                                        |
| token            | token值                                                |
| 请求结果:        |                                                        |
| 失败             | {"code": "ERROR", "message": "失败原因", "data": null} |

成功返回数据如下：

```json
{
    "code": "SUCCESS",
    "data": {
        "id":"用户id",
        "mobile": "⼿机号",
        "nickname": "昵称"
        "avatar_url": "头像url"
    },
    "message": null
}
```

## 11. 注册   <font color=gree>完成</font>

| 注册（创建token） | /user/create                                                 |
| ----------------- | ------------------------------------------------------------ |
| 请求方式          | post                                                         |
| Content-Type      |                                                              |
| 请求参数(Body)：  |                                                              |
| mobile            | 手机号                                                       |
| password          | 密码                                                         |
| nickname          | 昵称                                                         |
| 请求结果:         |                                                              |
| 失败              | {"code": "ERROR", "message": "失败原因", "data": null}       |
| 成功              | {"code": "SUCCESS", "data": {"token":"xxx",'id'=>'⽤户id'}, "message": null} |