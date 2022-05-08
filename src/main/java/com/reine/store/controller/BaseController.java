package com.reine.store.controller;

import com.reine.store.controller.ex.*;
import com.reine.store.service.ex.*;
import com.reine.store.util.JsonResult;
import com.reine.store.vo.ErrorCode;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * 控制层类的基类，
 * <p>
 * TODO 后续可将此类改为AOP
 *
 * @author reine
 * 2022/5/6 17:38
 */
public class BaseController {
    /**
     * 操作成功状态码
     */
    public static final int OK = 200;

    /**
     * 请求处理方法，方法返回值需要传递给前端
     *
     * @return 错误信息
     */
    @ExceptionHandler({ServiceException.class, FileUploadException.class})
    public JsonResult<Void> handlerException(Throwable e) {
        if (e instanceof UsernameDuplicateException) {
            return new JsonResult<>(ErrorCode.USERNAME_DUPLICATE);
        } else if (e instanceof InsertException) {
            return new JsonResult<>(ErrorCode.INSERT_USER_ERROR);
        } else if (e instanceof UserNotFoundException) {
            return new JsonResult<>(ErrorCode.USER_NOT_FOUND);
        } else if (e instanceof PasswordNotMatchException) {
            return new JsonResult<>(ErrorCode.PASSWORD_NOT_MATCH);
        } else if (e instanceof UpdateException) {
            return new JsonResult<>(ErrorCode.UPDATE_ERROR);
        } else if (e instanceof DeleteException) {
            return new JsonResult<>(ErrorCode.DELETE_ERROR);
        } else if (e instanceof AddressCountLimitException) {
            return new JsonResult<>(ErrorCode.ADDRESS_COUNT_LIMIT);
        }  else if (e instanceof AddressNotFoundException) {
            return new JsonResult<>(ErrorCode.ADDRESS_NOT_FOUND);
        } else if (e instanceof AccessDeniedException) {
            return new JsonResult<>(ErrorCode.ACCESS_DENIED);
        } else if (e instanceof FileSizeException) {
            return new JsonResult<>(ErrorCode.FILE_SIZE_ERROR);
        } else if (e instanceof FileTypeException) {
            return new JsonResult<>(ErrorCode.FILE_TYPE_ERROR);
        } else if (e instanceof FileStateException) {
            return new JsonResult<>(ErrorCode.FILE_STATE_ERROR);
        } else if (e instanceof FileEmptyException) {
            return new JsonResult<>(ErrorCode.FILE_EMPTY_ERROR);
        } else if (e instanceof FileUploadIOException) {
            return new JsonResult<>(ErrorCode.FILE_UPLOAD_IO_ERROR);
        }
        return null;
    }

    /**
     * 获取session中保存的对象uid
     *
     * @param session session对象
     * @return 当前登录用户的uid
     */
    protected final Integer getUidFromSession(HttpSession session) {
        return Integer.parseInt(session.getAttribute("uid").toString());
    }

    /**
     * 获取session中保存的用户username
     *
     * @param session session对象
     * @return 当前登录用户的username
     */
    protected final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }
}
