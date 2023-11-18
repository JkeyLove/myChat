package gcc.business.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /**
     * 姓名
     */
    String username;

    /**
     * 会员
     */
    Boolean isVip;
}
