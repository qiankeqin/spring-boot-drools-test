package xyz.spring4all.springbootdroolstest.utils;

import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.runtime.KieSession;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

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
     * @param classPath 绝对露肩
     * @return KieSession有状态
     * @throws Exception
     */
    public static KieSession newKieSession(String classPath) throws Exception{
//        KieSession kieSession = getKieBase(classPath)
        return kieSession;
    }

}
