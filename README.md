# springcloud_oauth2.0
一个基于Spring Cloud+OAuth2.0+Spring Security+Redis的统一认证脚手架

## 问题记录

### 1. 当在控制器方法中注入认证信息 Principal null
--- 这个时候需要考虑下是不是这个对外的接口是不是需要权限。这个接口不能被拦截
，每次有请求的时候要鉴权，访问设置的security.oauth2.resource.user-info-uri,获取用户授权信息 