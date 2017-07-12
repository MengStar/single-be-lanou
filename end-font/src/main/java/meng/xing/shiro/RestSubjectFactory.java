package meng.xing.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * Rest风格，无状态的SubjectFactory
 */
public class RestSubjectFactory extends DefaultWebSubjectFactory {
    @Override
    public Subject createSubject(SubjectContext context) {
        //context.setSessionCreationEnabled(false);//关闭session
        return super.createSubject(context);
    }
}
