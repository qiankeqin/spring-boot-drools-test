package xyz.spring4all.springbootdroolstest.utils;

import jdk.internal.util.xml.impl.Input;
import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 * @program: spring-boot-drools-test
 * @description: kieSession工具类
 * @author: qiankeqin
 * @create: 2018-12-29 19:28
 **/
public class KieSessionUtils {
    private static KieSession kieSession;
    private static final String RULES_PATH = "rules/";

    public KieSessionUtils() {
    }

    public static KieSession getAllRules() throws Exception{
        try{
            if(kieSession!=null){
                kieSession.dispose();
                kieSession = null;
            }
            KieServices kieServices = KieServices.Factory.get();
            KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
            for(org.springframework.core.io.Resource file : new PathMatchingResourcePatternResolver().getResources("classpath*:"+RULES_PATH+"*.*")){
                kieFileSystem.write(org.kie.internal.io.ResourceFactory.newClassPathResource(RULES_PATH+file.getFilename(),"UTF-8"));
            }
            final KieRepository kieRepository = KieServices.Factory.get().getRepository();
            kieRepository.addKieModule(new KieModule() {
                @Override
                public ReleaseId getReleaseId() {
                    return kieRepository.getDefaultReleaseId();
                }
            });
            KieBuilder kieBuilder = KieServices.Factory.get().newKieBuilder(kieFileSystem);
            kieBuilder.buildAll();
            kieSession =  KieServices.Factory.get().newKieContainer(kieRepository.getDefaultReleaseId()).newKieSession().getKieBase().newKieSession();
            return kieSession;
        } catch(Exception ex) {
            throw ex;
        }
    }


    /**
     * 快速新建KieSession
     * @param classPath 绝对路径
     * @return KieSession有状态
     * @throws Exception
     */
    public static KieSession newKieSession(String classPath) throws Exception{
        KieSession kieSession = getKieBase(classPath).newKieSession();
        return kieSession;
    }

    /**
     * 清空对象
     */
    public static void disposeKieSession(){
        if(kieSession!=null){
            kieSession.dispose();
            kieSession = null;
        }
    }

    protected static KieBase getKieBase(String classpath) throws Exception {
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kfs = kieServices.newKieFileSystem();
        Resource resource = kieServices.getResources().newClassPathResource(classpath);
        resource.setResourceType(ResourceType.DRL);
        kfs.write(resource);
        KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();
        if(kieBuilder.getResults().getMessages(Message.Level.ERROR).size()>0){
            throw new Exception();
        }
        KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
        KieBase kieBase = kieContainer.getKieBase();
        return kieBase;
    }

    /**
     * 根据服务器真实路径下的xls文件生成drl文件内容
     * @param realPath
     * @return
     * @throws FileNotFoundException
     */
    public static KieSession getKieSessionFromXls(String realPath) throws FileNotFoundException {
        return createKieSessionFromXls(getDRL(realPath));
    }

    /**
     * 将drl内容转化为session
     * @param drl
     * @return
     */
    public static KieSession createKieSessionFromXls(String drl) {
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl,ResourceType.DRL);
        Results results = kieHelper.verify();
        if(results.hasMessages(Message.Level.WARNING,Message.Level.ERROR)){
            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
            messages.forEach(System.out::println);
            throw new IllegalStateException("compilation errors were found,check the logs");
        }
        return kieHelper.build().newKieSession();
    }


    /**
     * 把xls解析成String
     * @param realPath
     * @return
     */
    public static String getDRL(String realPath) throws FileNotFoundException {
        File file = new File(realPath);
        InputStream inputStream = new FileInputStream(file);
        SpreadsheetCompiler spreadsheetCompiler = new SpreadsheetCompiler();
        return spreadsheetCompiler.compile(inputStream, InputType.XLS);
    }


}
