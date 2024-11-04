# 分布式计算

# 介绍
common：通用一些类型和方法\
master：服务注册和调用的主服务\
node：  计算节点，最终会注册到master上
# 使用手册
在node服务task文件下创建service，并且继承于TaskRunService类型，然后根据编写RunTask方法。\
在master中task文件下创建TaskFactory的子类，然后编写其三个方法，三个方法分别为registerTask，注册Task任务，finishOneTask完成一次Task调用，finishAllTask完成所有Task调用

可以通过/master/service/v1/getService和/master/service/v1/getAllTask查看任务情况

# 作者
White_Enzuo
拿来做云计算作业的