package com.campusactivity.core.User.controller;


import com.campusactivity.core.User.entity.SysUser;
import com.campusactivity.core.User.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author qihang
 * @since 2020-01-14
 */
@RestController
@RequestMapping("/User/sys-user")
public class SysUserController {


    @Autowired
    private SysUserServiceImpl sysUserService;

    /**
     * 批量添加用户
     */
    @RequestMapping("/add")
    public String addAll(){
        String [] names={"庆向山","所昊英","植冬","车雅懿","娄昂熙","甲枝","佼凝旋","留香薇","皇甫阳","辟雅致","毕干","建怜容","粟宝","荀夜梅","贾鸿哲","却紫安","仪飞掣","苍宏义","门绮露","邗喜","袭虹英","道冷亦","紫念真","汤澈","赫木","翠文瑞","禽令暎","节小翠","常楚洁","阮曼云","吉丰羽","南宫宜欣","乐正从蓉","捷盼秋","聊允","拱新觉","漆韦","勤令锋","平皓","运艳芳","及星星","镇君婷","圣华荣","靖静娴","钮密","茂冷菱","秋寒","郝圣杰","蹇清绮","侍康平","徐觅山","过沙羽","箕锐翰","裴昆峰","沙昱","薄方","环秀隽","逢晗玥","夫秀","第五洁","素韫素","廉启颜","尔仙","浦蔚","壤驷松雨","行恨","瞿若星","佘浩慨","郁沛蓝","翟淳静","东方羲","集文柏","桓惜寒","逮文惠","鄂畅然","史傲之","韩秀杰","呼延芷文","仉令秋","祈含莲","焦和裕","堵飞飙","淦睿诚","蔺初蝶","叔妙双","兆硕","须举","洪寒烟","泉高兴","宇文雅洁","鄢雅畅","阙若菱","睦依玉","禚霞月","习向文","枚友易","闻薇歌","况樱花","孟问蕊","綦婉静","费含云","池睿达","计芹","斐长卿","衣芮波","耿蓓蕾","吴乐英","独优扬","永冰安","出意","骑雅柏","金欣","板睿好","窦涛","长孙茜","候盼晴","茅成仁","石以晴","荣向露","巫马永新","鲜宛秋","僧嘉颖","敬雅容","苌天元","瓮和璧","郏天罡","隋半烟","纪丝微","应欣悦","潭香波","彭子薇","屠秋英","贡淑慧","寻心","琴依秋","山梦秋","璩平良","姓玥","晏霜","腾鹏翼","戊帆","库冷荷","逄阳朔","法流惠","源景山","卑璟","竹忻欢","载问风","谷梁莹然","崔清悦","宛飞燕","归永春","邬智敏","索运乾","鄞怡宁","甄晴霞","满文彬","校平卉","苑凌文","雍运珊","宿华乐","碧鲁明凝","宾沛岚","本绮晴","上官倚","禄盼香","马嘉禧","盈幼怡","速迎","肥锐立","喜涵蓄","陈星儿","万倚云","昔修竹","邱箫吟","性盼夏","塞清淑","佛曼彤","寿夏柳","慎痴海","台从灵","盍志强","夏坚成","不学林","康水卉","葛子琪","田文轩","望慧英","种文林","后志专","南门新梅","乌翠芙","府浩广","郸雍雅","童依薇","改芊芊","毋妤","南玮奇","刁翊君","疏康伯","书家美","仆霈","衷清心","崇诗双","曹之云","祢秀颖","羽南珍","帛飞捷","酒琬凝","释晶辉","哀玉堂","孝曼彤","畅香巧","城晗琴","第姣丽","王初翠","年思","夙孤丹","郁沛芹","答代容","蒯俊能","裘赡","陀晶滢","连巧荷","稽飞鸾","许阳","俎宛妙","乘曼文","考明俊","登苓","其涵涵","周夏岚","坚翠桃","濮阳宜嘉","在天材","巫海超","闾丘代芹","师望","勇昭懿","犁婉慧","化景明","闭和志","赖瀚文","竺滢渟","果慧晨","漫霁","骆欣彤","甫高达","双昂然","机谷槐","天琅","宦季萌","倪诗文","嬴骄","国暄嫣","受端","亥珠","艾梓","姜彭祖","汪谧","丛丹烟","谢惠美","凌梅","查阳州","储醉易","滕以蕊","诺映阳","藏昆卉","辛鸿信","钭冷霜","厚梦易","革昕妤","柏苍","张廖南烟","宫寒梅","寸慧心","暨白易","侨光济","段干良奥","水颖","皮曼冬","寇清芬","乌孙绿兰","错昂雄","解宵雨","益安春","郑雨双","佟德曜","伟高懿","展骊洁","牛傲南","燕晓莉","施夜蓉","鞠亦凝","柔谷翠","蓬健","边云霞","幸思涵","辜暖","酆君昊","冯凌春","钟离容","恽明智","傅傲丝","公孙含玉","姒若翠","沈贞婉","林鹏云","字飞丹","声映雁","慕容姿","学立群","桑初南","张简千柳","溥敏丽","御子昂","富察怜云","司徒秀曼","戎幼白","虢红豆","仙振翱","明良","封雪枫","唐畴","宜修能","检雪松","雪凡双","妫娅芳","蔡翠巧","谬沙雨","斛淳雅","荆娴婉","熊觅露","利访","说菲","商梦凡","寒智纯","剧南莲","柴吉玉","戢古韵","买鸿达","佟佳觅松","尤骞仕","戏维","芮曼雁","蒋水蓝","宣临","罕丽芳","仇痴凝","樊莹莹","韶梦凡","青弘雅","刑绿竹","邢鸿文","弘子楠","俟逸丽","奕嘉歆","祭念之","遇晶辉","强雨","千卓然","麻尔","庹梦丝","席凡雁","云永嘉","漆雕妙柏","时辰","诗和正","阎千山","班绮梅","凭振宇","笃英华","玄玟丽","慕绍元","谏夜卉","孔雪柳","定雨竹","旅君雅","易建元","功真如","闫乐悦","卓高阳","伦暄和","蒙骊媛","森知睿","费莫杰","首雨安","楼凌晴","农曼寒","占夜玉","母娜兰","龙灵安","牢书","左玄清","丙飞雪","谯略","帖展鹏","曲邵","冒云韶","苦海桃","世初珍","念丹亦","季光亮","隐天韵","檀弘毅","渠唱","系文康","卢寄柔","奈同方","撒冰菱","兰琪","聂雨筠","汉磬","黄慧艳","希嘉祯","信凡阳","蚁代桃","务德元","丹春柏","税素华","终鸿振","官阳辉","鲁豪","局新洁","锺乐逸","绪洛灵","后醉冬","厍又亦","盖宏爽","苏以轩","公叔泓","烟平莹","求悦和","银跃","勾长岳","太叔萧玉","舜浩大","逯鹏鹍","严星驰","贵睿彤","韦珏","咎千凝","介宛菡","彤梦蕊","诸景彰","牵绣文","扈晓丝","穰晓莉","回痴春","翦笑笑","公羊白柏","干子实","阳冰真","承艳芳","濯念烟","粘朗宁","僪乐蓉","姚夏岚","纳喇轩","宰父滨","称明钰","栗馨蓉","洛怀曼","秘念珍","扬茂勋","瑞安宁","香从云","麦华茂","岑兰蕙","让雁卉","空冠","令狐丽珠","资航","用芷荷","古傲霜","弓暄","冼宵晨","实乐心","白白翠","原燕妮","巢芳菲","波文","步英豪","刚醉波","伯州","桥芳蕙","公良赋","广婉仪","卿惜筠","豆代芹","任夏容","可弼","郦阳煦","郯璧","殳千凡","管雪","爱德馨","蔚安筠","贸玲琳","涂梅雪","宝贞芳","中英秀","钞思真","嵇学义","度杨柳","藩文漪","抄梦容","卜美","齐佁然","能秀洁","恭飞莲","毛娇洁","盘书凝","藤歆然","萧翠茵","矫雁菡","丰芷文","毓孤容","欧若英","威景行","江夏瑶","鱼语诗","何融雪","湛秀美","乾天悦","公西绮思","顾天韵","保兰泽","公冶鸿","奉芳馥","孛幼安","诸葛曼安","位银瑶","令韵诗","邸燕珺","京秋珊","鹿燕楠","蓝文茵","沐觅翠","将巧香","怀学真","汗颀秀","绍雨筠","庄瑜","巨如南","义奇水","来方方","章光辉","冠元蝶","越如雪","贯施然","谭梓瑶","仝鹏赋","赫连飞莲","是永","练念桃","巩才艺","邶筠","从怀莲","皇齐","庞运锋","贰一禾","修弘济","牧捷","塔彰","宁晖","督贤","邛晓燕","五博达","詹飞双","休婉","朋睿广","颛孙雅素","由枫","卯水荷","戈骥","龚代天","硕芸馨","励奇颖","士晓蕾","贲昕葳","悉精","铁运恒","赤令羽","夏侯碧玉","庚珍瑞","訾望慕","冷曼安","佴忆雪","旗雨文","微生迎彤","势香旋","融映雁","房白竹","富含香","赧冬菱","掌青文","焉宛白","亓飞槐","福桂芝","锺离利","廖婉清","芒延","昂清懿","驹孤","肇夏蓉","伍思萱","忻嘉宝","饶苒","飞戈","洋松","铎翔飞","无嫣","郭好洁","委振荣","尉迟暮雨","真婉仪","依冰薇","相向彤","司马绮山","穆丝","老璠瑜","纵成益","景建同","巧清懿","茆新之","轩辕昊然","桐蕴涵","尉冰蝶","甘新月","屈同甫","孙又松","禾静婉","符平彤","文梓楠","匡清霁","梁丘天和","桂念文","向庆雪","尾水风","之令怡","米涵亮","梅子爱","钟阳曦","嘉秀艳","泥欣荣","魏惜玉","方雪莲","晁曼丽","太史驰文","宗政昕雨","养清芬","丁英逸","鲍鸿达","线冬莲","钦夏云","井温纶","糜婷美","随蕾","温觅","祝逸春","俞幻巧","九晴虹","犹山蝶","靳振海","邓芳蔼","闵安青","琦雪晴","束芳春","武景铄","项凌丝","言旭尧","端木香梅","布迎丝","闽雨凝","单沛槐","隽水冬","颜澜","蒿以南","华谷之","支高朗","丑念雁","袁青旋","晋芙蓉","友觅荷","夕锦","西门慧颖","树叶芳","风沛萍","吕盼波","才璟","斋旭东","营梦兰","刘寄南","宰旎","赛流丽","羊舌斯乔","成辰骏","殷巧蕊","那姝丽","泣傲易","召夏柳","司元","郗清怡","冀香桃","续默","枝和硕","似奕奕","包益","纳怀芹","路逸","贝天曼","澹台央","范双","己夜天","莱潍","凤晴霞","皋飞星","仲诗蕊","简元良","朱寄瑶","茹燕","偶玉宇","始弘文","壬气","楚修洁","蒉濡","东郭秋春","戚馨欣","容莘","拓跋慕凝","多莺","侯立诚","仍柔","乌雅夏之","衅伟茂","衡依波","顿会","哈文漪","闻人飞雨","貊方仪","表哲茂","通梦晨","泷飞荷","花靖之","司寇勤","剑兴文","罗乐语","段以珊","进靖柏","全诗珊","仁紫雪","梁郎","胥雅柏","泰忆柏","汝骊娟","于豆","乔凝莲","慈琨瑶","繁芷珊","愈淑贞","马佳怀玉","阿冰洁","频春翠","谷晓星","敛心语","允思茵","针芬","单于涵梅","喻鸿哲","舒映冬","宗思柔","呼绮","闳家美","缑清淑","百映雪","申鸿博","浮陶宜","謇仙仪","夹谷忆灵","伊强","碧年","红俊雄","亓官芸若","仰力学","栾嘉志","礼旎旎","律荌","英华","萨安白","长运杰","缪春翠","延铄","印芳泽","厉彤云","业小琴","申屠成荫","程盼晴","范姜咸","柳乐欣","区令慧","象清逸","霜妮子","典陶然","游兴邦","充耀","扶诗柳","零照","抗轩秀","类代梅","淳于蓓","子车笑天","告承载","荤念真","元鹏煊","卷映安","杨琭","东门飞兰","万俟觅双","李芳春","完微澜","臧涵阳","关访烟","邝英媛","止令雪","家元冬","汲鸥","和长文","咸童童","员柔怀","邵庄","虞以冬","战夏山","接宣","钊凌春","董项禹","笪思烟","巴天路","悟弘伟","胡乐芸","镜若薇","苟觅翠","力尔蝶","卫永元","羿英资","么斯伯","蓟丽华","敏晓燕","赵夜梦","迟昊宇","有听枫","左丘水竹","谌智宸","脱逸致","隗如柏","覃弘","闪诗翠","赏心菱","仲孙访风","尹凌春","北春岚","辉婉淑","麴清绮","菅思淼","代薇","智尔蓝","松欣笑","但蕴和","夷攸","霍云蔚","守起运","雀乔","褚依云","亢广君","浑幻翠","祖槐","宋寻凝","堂辰阳","权芫华","迮阑","阴迎曼","邹天巧","羊凡白","狄昊苍","摩寿","星问萍","潜嘉丽","东夏烟","问成业"};
        int studentId=4;
        for(String name:names ){
            SysUser temp=new SysUser(name,"123456",String.valueOf(studentId++));
            sysUserService.save(temp);
        }
        return "success";
    }
}

