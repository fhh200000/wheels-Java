package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import defs.Student;

public class StudentGenerator {
	public static void main(String[] args) {
		String[] studentnames = {"余启强","方振宇","尹广杰","胡卿远","李昀臻","刘万杰","蔡羽杰","曹慧惠","黄雪燕","李韵","林仪","吕蕊","吴思颖","朱丽娜","禅润泽","陈星宇","付兆海","何淳炜","何锝升","黄奔鹏","姬金鹏","金子涵","李喆","梁梓源","林元澍","陆钦猫","罗勤涛","毛俊哲","孟子涵","倪志扬","沈汉龙","盛荣凯","汤立炜","唐嘉远","田旭","王新毅","邬岸真","吴宇晨","邢俊元","薛铭豪","姚宗昊","俞政男","章顺峰","周翔","黄羽书","陈雨","蒋雨婷","马芳炜","许憬卓","张译文","庄舒涵","蔡梓涵","陈界宇","陈彦龙","董致远","傅昊航","何佳诚","胡哲川","黄诚煊","金辰诺","孔豪辰","雷岳衡","励松泽","林苑靖","刘威","罗昱","吕嘉庆","蒙汝峰","潘炬豪","沈世航","唐梦鑫","王佳旺","王佳禹","王翔","王宣东","王彦祺","吴高峰","吴浴钒","徐柏遥","严俊杰","叶兰锋","袁其澎","张杰","章威","周旭锋","郑乾辉","龚盼","钱佳佳","叶婉婷","朱子琪","包涵","蔡铭灏","陈佳希","陈军","陈祥乐","陈奕衡","干海洋","洪漂","胡凯平","黄懂","贾俊飞","金凯凯","来鸣杰","李泽轩","梁爽","刘聪健","马冲","马华晋","莫云龙","潘亚锋","沈天添","陶鼎立","王家豪","王萱","魏天","吴凌斌","吴宁","吴浙锋","徐翊皓","颜俊伟","叶志豪","詹戈学","章志豪","钟郑奥","周志鹏","朱展鹏","胡诗佳","黄湘烨","汤紫晗","尹燕","应婕妮","蔡庆安","陈如聪","陈升","崔高宾","高炜","侯泽启","胡星成","黄泽","金利杰","景昱东","李恩惠","林泊帆","刘树范","马晨钟","马舜翔","彭明强","钱戈弋","宋方涛","屠温博","王力","王屹峰","吴铭玮","夏茂聪","徐扬","许家骅","杨浩","杨凌洁","张浩栋","赵镇炀","周泰羽","周祥","郭稼霖","李雅琪","王鸿媛","周杉杉","曾庆拓","陈润","程俊亮","戴凌昊","方嘉俊","龚镓豪","郭君伟","胡嘉跃","黄旭","季琛博","金润华","李绍冲","林海翔","柳觉顺","楼亦安","罗宗宇","毛一枭","潘治江","钱尚伟","乔晓磊","孙鸿发","汪霄","王涛","王振国","吴艺伟","向洪冲","徐耀晨","杨天汶","姚寒皓","应择然","余新成","张杰飞","赵峰","郑煜钿","朱永灿","朱云涛","徐莹俊","王宇涛","杨鸿斌","尤鼎元","詹川玮","潘志杰","王珏盛","鲍滢","梁津津","骆姚沁霖","毛舒瑶","孙楠","王雅","朱菡清","陈恒亮","陈文伟","杜鹏","冯泽杰","郝文翀","何涔涌","胡叶骏","黄子桓","江欣扬","林勇俊","刘闯","陆其锴","毛宗泱","莫梓言","沈辰宁","孙宇豪","王辰昱","翁啸洋","吴逸潇","谢星全","杨智平","姚烨涛","余洋","张豪","张子毅","周帝豪","周禧楠","卓熠亮","高翔","何小艺","沈佳意","叶芝宏","包勋昊","柴郢男","陈启明","褚嘉豪","樊雨博","范祥烨","高铭杰","洪煜星","黄阳播","金磊","金永顺","黎扬韬","廖凯特","刘督","龙佳文","马勤涵","彭宏成","戚骏龙","沈泽飞","孙杰","童烨韬","王镭龙","王雪宇","吴明洲","吴文杰","吴源飞","夏凌宇","徐瑞凡","杨铖","叶中华","余家宝","张晗烁","张奇皓","赵灵飞","周子寒","胡馨月","王恩寻","颜家姚","赵蝶依伊","曾国丰","陈睿杰","陈石","陈晢","陈子昱","戴道良","葛超杰","过昊天","胡杭凯","黄展鹏","蒋高畅","金琪波","李骏林","林程镨","林稼轩","刘子瑜","吕由甲","毛文涛","聂源樟","钱佳尉","宋加俊","万嘉豪","王书臣","王银凯","吴晟","夏禹轩","徐杨","徐洋扬","杨必扬","杨华","杨青山","杨为储","应凌浩","张嘉豪","张亚男","郑佳琪","周致远","朱奇龙","刘博金涵","刘姝婧","王思瑶","吴慧芳","周亚平","柴潇强","陈天宇","单兴龙","邓贤波","方周祺","顾镕杰","何飞宇","何泽俭","胡禄阳","金文斌","李腾","李宇航","林津锋","卢炯恺","卢泽洋","茅贤琪","邱君城","孙科","孙思达","王斌","王淘","王伟涛","王子豪","吴陈增","吴逸飞","武宇鹏","肖泽丰","徐哲扬","姚家浩","尤佳楠","袁震宏","张达文","张永顺","赵曜","周晨阳","祝承汉","罗依蒙","王星慧","王焱琪","周圆","曾益宽","陈超","陈伟享","董子豪","冯余峰","葛亮","顾睿帆","胡鑫","霍永然","贾皓","金泽宇","李朕坤","林涛","卢文凯","罗宇航","莫俊杰","商世杰","孙伟杰","王才民","王炜炜","王籽翔","吴逸航","谢华俊","徐子涵","姚雪辉","于宇","余金海","张梁辉","张智宁","张子鹏","周浩东","庄子琛","邹桥东","石在天","汪洋","赵翊斐","李桂缘","盛颖颖","金敏杰","徐江涛","倪锴俊","瞿年","李煜晓","夏菁"};
		String[] studentids = {"16901132","17905113","189010334","189010413","189050119","189050123","189050201","189050202","189050203","189050204","189050205","189050206","189050207","189050208","189050209","189050211","189050212","189050213","189050214","189050215","189050216","189050218","189050219","189050220","189050221","189050222","189050223","189050224","189050225","189050226","189050227","189050228","189050229","189050230","189050231","189050233","189050234","189050235","189050236","189050237","189050239","189050240","189050241","189050242","189050243","189050301","189050302","189050303","189050304","189050305","189050306","189050307","189050308","189050309","189050310","189050311","189050312","189050313","189050314","189050315","189050316","189050317","189050318","189050319","189050320","189050321","189050322","189050323","189050324","189050325","189050326","189050327","189050328","189050329","189050330","189050331","189050332","189050333","189050334","189050335","189050336","189050338","189050339","189050340","189050342","189050343","189050401","189050402","189050403","189050404","189050405","189050406","189050407","189050408","189050409","189050410","189050411","189050412","189050413","189050414","189050415","189050416","189050417","189050418","189050419","189050420","189050421","189050422","189050423","189050424","189050425","189050426","189050427","189050429","189050430","189050431","189050432","189050433","189050435","189050436","189050437","189050438","189050439","189050440","189050441","189050442","189050601","189050602","189050603","189050604","189050605","189050606","189050607","189050608","189050609","189050610","189050611","189050612","189050613","189050614","189050615","189050616","189050617","189050618","189050620","189050621","189050622","189050623","189050624","189050625","189050626","189050628","189050629","189050630","189050632","189050633","189050634","189050635","189050638","189050639","189050640","189050641","189050801","189050802","189050803","189050805","189050806","189050807","189050808","189050809","189050810","189050811","189050812","189050813","189050814","189050815","189050816","189050818","189050819","189050820","189050821","189050822","189050823","189050824","189050825","189050826","189050827","189050828","189050829","189050830","189050831","189050832","189050833","189050834","189050835","189050836","189050837","189050838","189050839","189050840","189050841","189050842","189060236","189200329","189200432","1890927104","17905538","189010123","189010130","189050101","189050102","189050103","189050104","189050105","189050106","189050107","189050108","189050109","189050110","189050111","189050112","189050113","189050114","189050115","189050116","189050121","189050122","189050124","189050125","189050126","189050127","189050128","189050129","189050131","189050132","189050133","189050135","189050136","189050137","189050138","189050139","189050140","189050141","189050142","189050143","189050501","189050502","189050504","189050505","189050506","189050508","189050509","189050510","189050511","189050512","189050513","189050514","189050515","189050516","189050517","189050518","189050519","189050521","189050522","189050523","189050524","189050525","189050526","189050527","189050528","189050529","189050530","189050531","189050532","189050533","189050534","189050536","189050537","189050538","189050539","189050540","189050541","189050542","189050701","189050702","189050703","189050704","189050705","189050706","189050707","189050708","189050709","189050710","189050711","189050712","189050713","189050714","189050715","189050716","189050717","189050718","189050719","189050720","189050721","189050722","189050723","189050724","189050725","189050726","189050727","189050728","189050729","189050730","189050731","189050732","189050733","189050734","189050735","189050736","189050737","189050738","189050739","189050740","189050741","189050742","189050901","189050902","189050903","189050904","189050905","189050906","189050907","189050908","189050909","189050910","189050911","189050912","189050913","189050914","189050916","189050917","189050918","189050919","189050920","189050921","189050922","189050923","189050924","189050925","189050926","189050927","189050928","189050929","189050930","189050931","189050932","189050933","189050934","189050935","189050936","189050937","189050938","189050939","189050940","189050941","189050942","189051001","189051002","189051003","189051004","189051005","189051006","189051007","189051008","189051009","189051010","189051011","189051013","189051014","189051015","189051016","189051018","189051019","189051020","189051021","189051022","189051023","189051024","189051025","189051026","189051027","189051029","189051030","189051031","189051032","189051033","189051034","189051035","189051036","189051037","189051039","189051041","189051042","189200224","189200225","189200235","189200401","189200403","189200416","189200431","189200620","189200621","189200802","189200805"};
		int[] 	 studentscores= {85,86,66,88,8,29,77,65,79,58,77,77,71,75,74,78,56,69,74,80,69,69,58,76,73,74,75,40,60,60,55,37,76,44,60,79,83,66,76,68,53,78,78,76,76,74,72,78,78,72,72,64,23,69,13,91,81,70,71,76,77,58,78,76,66,70,67,68,86,79,72,75,83,80,74,72,85,72,70,78,82,81,63,75,77,36,82,82,72,77,84,86,69,74,73,76,71,71,74,82,69,80,78,65,77,72,69,76,63,76,72,67,76,64,65,74,68,70,76,79,84,75,86,69,71,69,82,86,69,79,72,76,58,88,75,52,66,70,73,64,73,85,59,66,51,74,84,70,71,72,50,70,65,82,85,73,59,75,77,61,61,73,84,65,75,86,69,76,53,81,75,65,46,73,69,76,72,64,83,77,72,55,73,54,50,76,80,66,78,78,29,78,80,31,82,83,36,44,38,64,77,58,80,84,89,69,78,76,86,79,77,85,78,81,66,81,80,77,88,79,78,86,81,90,69,81,57,76,79,81,56,83,91,89,83,70,50,75,76,69,77,65,72,76,58,80,67,76,66,66,68,77,79,49,75,52,58,78,64,61,74,47,69,75,71,72,78,66,72,74,40,22,68,40,72,79,80,75,76,84,66,84,74,75,19,69,61,57,56,56,79,76,78,67,79,76,69,55,75,54,71,81,82,66,72,71,73,81,67,89,86,72,68,72,92,43,57,37,72,35,66,80,83,85,81,59,69,82,74,71,84,89,81,58,60,71,9,50,76,67,31,72,66,70,72,74,79,27,77,57,43,81,54,71,74,67,62,87,77,87,86,69,68,63,45,34,81,80,59,83,51,83,78,70,79,94,17,78,87,68,84,79,73,86,70,83,86,67,80,88,79,69,73,72,62,59,76,79,65,64,53,51,83,79,72,63,81,77,76,86,84,84,72,82};
		Student[] students = new Student[studentids.length];
		for(int i=0;i<students.length;i++)
			students[i] = new Student(studentids[i],studentnames[i],studentscores[i]);
		System.out.println("请输入保存的文件名：");
		Scanner sc = new Scanner(System.in);
		String filename = sc.next();
		filename+=".students";
		try {
			File outfile = new File(System.getProperty("java.class.path")+"/"+filename);
			if(!outfile.exists())
				outfile.createNewFile();
			FileOutputStream fos = new FileOutputStream(outfile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(students);
			oos.flush();
			oos.close();
			System.out.println("学生信息"+outfile.getAbsolutePath()+"已保存。");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		sc.close();
	}
}
