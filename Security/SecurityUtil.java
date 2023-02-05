
import com.fileServer.entity.UserPo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {

    public String getUserName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public Long getUserId(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserPo){
            UserPo userPo = (UserPo) principal;
            return userPo.getUserId();
        }
        return null;
    }

}
