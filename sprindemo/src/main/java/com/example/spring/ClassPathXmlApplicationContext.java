package com.example.spring;

import com.example.exception.BeanNotFountException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassPathXmlApplicationContext implements  BeanFactory {

    private Map<String, Object> beans = new HashMap<String, Object>();

//    public ClassPathXmlApplicationContext() throws Exception{
//        SAXBuilder sb=new SAXBuilder();  //使用的是默认的解析器
//        /**
//         * 得到根元素：
//         *         首先，调用对象的getClass()方法是获得对象当前的类类型，这部分数据存在方法区中，
//         *         而后在类类型上调用getClassLoader()方法是得到当前类型的类加载器，我们知道在Java中所有的类都是通过加载器加载到虚拟机中的，
//         *         而且类加载器之间存在父子关系，就是子知道父，父不知道子，这样不同的子加载的类型之间是无法访问的（虽然它们都被放在方法区中），
//         *         所以在这里通过当前类的加载器来加载资源也就是保证是和类类型同一个加载器加载的。
//         *         最后调用了类加载器的getResourceAsStream()方法来加载资源。
//         *
//         */
//        Document doc = sb.build( this.getClass().getClassLoader().getResourceAsStream("beans.xml"));
//        // 得到Document，我们以后要进行的所有操作都是对这个Document操作的：
//        Element root= doc.getRootElement();
//        // 得到节点的集合： 这表示得到“bean”元素的所在名称为“book”的元素，并把这些元素都放到一个List集合中
//        List<Element> list= root.getChildren("bean");
//        for( int i=0; i<list.size();i++){
//            Element element=(Element)list.get(i);
//            String id = element.getAttributeValue("id");
//            String  clazz = element.getAttributeValue("class");
//            System.out.println(id+"："+clazz);
//            Object  o = Class.forName(clazz).newInstance();
//            beans.put(id, o);
//        }
//    }

    public ClassPathXmlApplicationContext() throws Exception{
        SAXBuilder sb=new  SAXBuilder();
        Document doc = sb.build( this.getClass().getClassLoader().getResourceAsStream("beans.xml"));
        Element root= doc.getRootElement();
        List<Element> list=root.getChildren("bean");
        for( int i=0; i<list.size();i++){
            Element element=(Element)list.get(i);
            String id = element.getAttributeValue("id");
            String  clazz = element.getAttributeValue("class");
            String  scope = element.getAttributeValue("scope");
            if(scope==null){
                scope="singleton";
            }
            Object  o = Class.forName(clazz).newInstance();
            if(scope.equals("singleton")){
                beans.put(id, o);
            }

            //下面是读取property属性
            for(Element propertyElement : (List<Element>) element.getChildren("property")  ){
                String name = propertyElement.getAttributeValue("name");
                String bean = propertyElement.getAttributeValue("bean");
                Object beanObject = beans.get(bean);

                //这里组装set操作方法名，将参数中的bean取出来，进行set拼接
                String methodName ="set"+name.substring(0,1).toUpperCase() + name.substring(1);
                System.out.println("method name ="+ methodName);

                try {
                    //这里实现了动态装配，调用Method的invoke方法,用了java的反射机制
                    Method m=o.getClass().getMethod(methodName, beanObject.getClass().getInterfaces()[0]);
                    m.invoke(o, beanObject);
                }catch (Exception e){
                    e.printStackTrace();
                    throw new BeanNotFountException("你的Bean属性"+name+"没有对应的set方法");
                }



            }
        }
    }

    private  Object createBean(String name) throws Exception{
        SAXBuilder sb=new  SAXBuilder();
        Document doc = sb.build( this.getClass().getClassLoader().getResourceAsStream("beans.xml"));
        Element root= doc.getRootElement();
        List<Element> list=root.getChildren("bean");
        for( int i=0; i<list.size();i++) {
            Element element = (Element) list.get(i);
            String id = element.getAttributeValue("id");
            String clazz = element.getAttributeValue("class");
            if(id.equals(name)){
                Object  o = Class.forName(clazz).newInstance();
                return  o;
            }
        }
        throw  new BeanNotFountException("No bean named '"+name+"' available");
    }


    @Override
    public Object getBean(String name) throws Exception {
        Object object= beans.get(name);
        if(object==null){
            return  createBean(name);
        }
        return  createBean(name);
    }
}
