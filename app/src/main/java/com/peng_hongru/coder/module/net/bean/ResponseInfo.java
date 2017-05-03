package com.peng_hongru.coder.module.net.bean;

import java.util.List;

/**
 * Created by 彭鸿儒 on 2017/4/18.
 * 邮箱：peng_hongru@163.com
 */

public class ResponseInfo<T> {


    /**
     * error : false
     * results : [{"_id":"58fca8b0421aa9544b77405f","createdAt":"2017-04-23T21:14:24.358Z","desc":"几分钟瞎谈恐怖片《林中小屋》中人物的真实关系","publishedAt":"2017-04-26T11:30:43.767Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av9879906/","used":true,"who":"LHF"},{"_id":"58ff6fbc421aa9544825f90e","createdAt":"2017-04-25T23:48:12.455Z","desc":"Amazing Open Source Android Apps","publishedAt":"2017-04-26T11:30:43.767Z","source":"web","type":"Android","url":"https://blog.mindorks.com/android-amazing-open-source-apps-e44f520593cc","used":true,"who":"AMIT SHEKHAR"},{"_id":"58ffe1b5421aa9544ed889e6","createdAt":"2017-04-26T07:54:29.874Z","desc":"4-26","publishedAt":"2017-04-26T11:30:43.767Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-25-13651793_897557617014845_571817176_n.jpg","used":true,"who":"daimajia"},{"_id":"590010ef421aa954511ebf12","createdAt":"2017-04-26T11:15:59.442Z","desc":"Render React components to Sketch ","images":["http://img.gank.io/3d1e78fe-137a-4830-8607-3b4555cb9c7f"],"publishedAt":"2017-04-26T11:30:43.767Z","source":"chrome","type":"前端","url":"https://github.com/airbnb/react-sketchapp","used":true,"who":"奥卡"},{"_id":"5900114a421aa9544b774079","createdAt":"2017-04-26T11:17:30.785Z","desc":"贝塞尔曲线 高级教程","publishedAt":"2017-04-26T11:30:43.767Z","source":"chrome","type":"拓展资源","url":"https://pomax.github.io/bezierinfo/zh-CN/","used":true,"who":"代码家"},{"_id":"590011da421aa9544ed889e8","createdAt":"2017-04-26T11:19:54.588Z","desc":"iOS 睡眠波纹效果，酷酷的~","images":["http://img.gank.io/396f2390-1066-49e3-b895-b991ad48ff1b"],"publishedAt":"2017-04-26T11:30:43.767Z","source":"chrome","type":"iOS","url":"https://github.com/KrisYu/Water","used":true,"who":"代码家"},{"_id":"5900120d421aa9544b77407b","createdAt":"2017-04-26T11:20:45.838Z","desc":"Android TensorFlow MNIST 数据库数字识别","images":["http://img.gank.io/620a2918-9f60-4eb2-b095-b338fe6a2dd5"],"publishedAt":"2017-04-26T11:30:43.767Z","source":"chrome","type":"拓展资源","url":"https://github.com/mari-linhares/mnist-android-tensorflow","used":true,"who":"代码家"},{"_id":"590012a0421aa9544ed889e9","createdAt":"2017-04-26T11:23:12.770Z","desc":"Android 浮动 Debug 工具箱，直接在浮动窗口执行 Android 测试功能。","images":["http://img.gank.io/7417e149-112a-466c-8124-b73c161ee866"],"publishedAt":"2017-04-26T11:30:43.767Z","source":"chrome","type":"Android","url":"https://github.com/hulab/debugkit","used":true,"who":"代码家"},{"_id":"58fb694b421aa9544ed889c3","createdAt":"2017-04-22T22:31:39.668Z","desc":"lykchat信息发送系统是Python3开发的，通过模拟微信网页端，基于个人微信号，为系统管理人员提供信息发送工具。","images":["http://img.gank.io/767e8b77-5813-47a3-aa29-3f42de997db4"],"publishedAt":"2017-04-25T13:11:39.357Z","source":"web","type":"拓展资源","url":"https://github.com/lykops/lykchat/","used":true,"who":"lyk-ops"},{"_id":"58fc9faf421aa9544ed889ca","createdAt":"2017-04-23T20:35:59.821Z","desc":"【脑洞菌】暴走的绿巨人！速读浩克星球与浩克世界大战","publishedAt":"2017-04-25T13:11:39.357Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av9927767/","used":true,"who":"LHF"},{"_id":"58fddc9a421aa954511ebf00","createdAt":"2017-04-24T19:08:10.208Z","desc":"横幅广告轮播控件","images":["http://img.gank.io/ffa66550-643a-429c-8e4c-53b51827a637"],"publishedAt":"2017-04-25T13:11:39.357Z","source":"web","type":"Android","url":"https://github.com/czy1121/bannerview","used":true,"who":"ezy"},{"_id":"58fe0496421aa954511ebf01","createdAt":"2017-04-24T21:58:46.939Z","desc":"Android虚拟机检测库，意在未授权的情况下禁止在虚拟机App上非法运行","images":["http://img.gank.io/88159d8b-e69f-4f79-8ef4-72c3c9f2289e"],"publishedAt":"2017-04-25T13:11:39.357Z","source":"web","type":"Android","url":"https://github.com/bunnyblue/AntiVM","used":true,"who":"ZQiang94"},{"_id":"58fe8e05421aa9544b77406d","createdAt":"2017-04-25T07:45:09.55Z","desc":"4-25","publishedAt":"2017-04-25T13:11:39.357Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-24-18013547_1532023163498554_215541963087151104_n.jpg","used":true,"who":"dmj"},{"_id":"58fea251421aa9544ed889da","createdAt":"2017-04-25T09:11:45.13Z","desc":"Java 图片本地压缩框架，节省流量必备之选。","publishedAt":"2017-04-25T13:11:39.357Z","source":"chrome","type":"Android","url":"https://github.com/Sunzxyong/Tiny","used":true,"who":"代码家"},{"_id":"58fea4b0421aa9544b774070","createdAt":"2017-04-25T09:21:52.202Z","desc":"找出并退订所有 Gmail 的订阅邮件，UnRoll.me 的开源版本。","images":["http://img.gank.io/5830e8b0-a752-4eb2-ac64-5875d911f837"],"publishedAt":"2017-04-25T13:11:39.357Z","source":"chrome","type":"拓展资源","url":"https://github.com/labnol/unsubscribe-gmail","used":true,"who":"代码家"},{"_id":"58fea4e2421aa954511ebf06","createdAt":"2017-04-25T09:22:42.671Z","desc":"可能是最好看的 v2ex iOS 客户端。","images":["http://img.gank.io/b8cde283-2ddb-4bdf-af2a-6a0b0ea65b80"],"publishedAt":"2017-04-25T13:11:39.357Z","source":"chrome","type":"iOS","url":"https://github.com/darkerk/v2ex","used":true,"who":"代码家"},{"_id":"58feb494421aa954511ebf07","createdAt":"2017-04-25T10:29:40.65Z","desc":"波尼音乐","images":["http://img.gank.io/c0195152-1040-4fde-ba24-1002fcc181af","http://img.gank.io/ea17515e-bb5f-4ee9-b91e-074d3c23baae"],"publishedAt":"2017-04-25T13:11:39.357Z","source":"chrome","type":"Android","url":"https://github.com/wangchenyan/PonyMusic","used":true,"who":"Jason"},{"_id":"58feb831421aa9544ed889dd","createdAt":"2017-04-25T10:45:05.234Z","desc":"成为游戏开发者之路","publishedAt":"2017-04-25T13:11:39.357Z","source":"chrome","type":"拓展资源","url":"https://github.com/miloyip/game-programmer","used":true,"who":"带马甲"},{"_id":"58feb89a421aa9544ed889df","createdAt":"2017-04-25T10:46:50.265Z","desc":"让 Swift 列表 Bouncy 起来~~ 弹弹弹~~","images":["http://img.gank.io/f4176cec-cdbf-4383-928b-cb228b5b35a5"],"publishedAt":"2017-04-25T13:11:39.357Z","source":"chrome","type":"iOS","url":"https://github.com/roberthein/BouncyLayout","used":true,"who":"代码家"},{"_id":"58f9aa1e421aa9544b77404c","createdAt":"2017-04-21T14:43:42.658Z","desc":"弹窗动画神器","images":["http://img.gank.io/af502228-d5cf-4b2f-9e5e-7f3221ca8d44"],"publishedAt":"2017-04-24T11:35:03.680Z","source":"web","type":"iOS","url":"https://github.com/loopeer/AlertTransition","used":true,"who":null}]
     */

    private boolean error;
    private List<T> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
