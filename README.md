# salary-management:   jdbc+servlet+filter+druid+layui+ajax+MD5
salary management system: Based on departments and positions, distribute salary according to personal conditions.

# 项目需求
## 背景资料：
* 某单位：管理人员、财务人员、技术人员和销售人员；
* 该单位下设4个科室：经理室、财务科、技术科和销售科；
* 工资由基本工资、绩效工资和奖金构成，失业保险和住房公积金在工资中扣除；
* 每个员工的基本资料有姓名、性别、年龄、单位，每个员工只属于1个科室；
* 每月个人的工资按月发放，实际发放的工资金额为工资减去扣除。
## 设计要求：
* 录入科室信息；
* 实现按照科室录入个人的基本资料、工资和扣除金额；
* 计算个人的实际发放工资；
* 按科室、职业分类查询人数和工资金额；
* 能够删除辞职人员的数据。
* 管理员基本信息的增删查改，加密登录系统；

# 项目简介
**工资管理系统（jdbc+servlet+filter+druid+layui+ajax+MD5）**

### 开发工具
Eclipse MySQL JDK Navicat Tomcat
### 核心技术
JDBC druid JSP+Servlet LayUI MD5 filter 反射
### 功能描述
* 使用 DBUtil 的方式封装对数据库操作的方法，其中使用泛型、反射，达到查询数据库并动态返回实体类对象的效果。
* 创建一个 BaseServlet，继承 HttpServlet，重写 doGet 方法，利用反射的方式获取各个 Servlet 的方法并执行，达到了封装减少代码量的效果。
* 用户登录，使用过滤器， session 中是否存在当前用户，若不存在，跳转到登录页面。
* 对密码进行 MD5 加密，在数据库存密文，保证密码明文的安全。
* 使用过滤器，过滤未登录不能使用的界面，达到强制登录的目的。
* 在设置下拉列表框时，使用 ajax 异步请求，实现下拉列表框二级联动。

# 效果如下
* **1.登录**  设置过滤器，当前登录用户不再登录，否则进入登录页面
![Image text](https://raw.githubusercontent.com/sarah-liushasha/salary/master/image-source/1.png)
* **2.科室管理**
![Image text](https://raw.githubusercontent.com/sarah-liushasha/salary/master/image-source/2(1).png)
* 添加新的科室
![Image text](https://raw.githubusercontent.com/sarah-liushasha/salary/master/image-source/2(2).png)
* 根据科室名模糊查询科室信息
![Image text](https://raw.githubusercontent.com/sarah-liushasha/salary/master/image-source/2(3).png)
* **3.员工管理**
* 编辑修改员工基本信息：查询全部科室信息，采用下拉菜单选择科室；删除按钮删除辞职的员工
![Image text](https://raw.githubusercontent.com/sarah-liushasha/salary/master/image-source/3(1).png)
* 查询按钮根据员工姓名模糊查询员工基本信息；新增按钮添加员工
![Image text](https://raw.githubusercontent.com/sarah-liushasha/salary/master/image-source/3(2).png)
* **4.工资管理** 新增按钮发工资 
* 发放工资根据科室查找员工
![Image text](https://raw.githubusercontent.com/sarah-liushasha/salary/master/image-source/4(1).png)
* 根据月份查询或者员工名字模糊查询发放工资信息
![Image text](https://raw.githubusercontent.com/sarah-liushasha/salary/master/image-source/4(2).png)
* **5.系统用户管理** 添加、查询、修改系统用户信息
![Image text](https://raw.githubusercontent.com/sarah-liushasha/salary/master/image-source/5.png)

##### 提示
* admin   123456
* 访问主页   http://localhost:8080/index
