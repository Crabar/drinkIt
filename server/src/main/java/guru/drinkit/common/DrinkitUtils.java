package guru.drinkit.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

public class DrinkitUtils {

    public static final Logger LOGGER = LoggerFactory.getLogger("CommonLogging");

    public static void logOperation(String operation, Object obj) {
        String info = String.format("%s: %s", operation, obj);
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            info += " by user: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        LOGGER.info(info);
    }

//    /**
//     * Retrieve userId from security context
//     *
//     * @return '0' for anonymous, 'userId' if user authorized and is not admin, otherwise return null;
//     */
//    public static Integer getCurrentUserId() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null) {
//            if (authentication.getAuthorities().size() != 0 &&
//                    authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ANONYMOUS"))) {
//                return  0;
//            } else if (!((UserDetails) authentication.getPrincipal()).getAuthorities()
//                    .contains(new SimpleGrantedAuthority(Role.ROLE_ADMIN.name()))) {
//                return ((DetailedUser) authentication.getPrincipal()).getUserId();
//            }
//        }
//        return null;
//    }

}