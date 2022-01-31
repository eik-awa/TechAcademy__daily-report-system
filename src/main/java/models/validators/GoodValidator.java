package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.EmployeeView;
import actions.views.GoodView;
import actions.views.ReportView;
import constants.MessageConst;

/**
 * 日報インスタンスに設定されている値のバリデーションを行うクラス
 */
public class GoodValidator {

    /**
     * 日報インスタンスの各項目についてバリデーションを行う
     * @param rv 日報インスタンス
     * @return エラーのリスト
     */
    public static List<String> validate(GoodView gv) {
        List<String> errors = new ArrayList<String>();

        //タイトルのチェック
        String employeeError = validateEmployee(gv.getEmployee());
        if (!employeeError.equals("")) {
            errors.add(employeeError);
        }

        //内容のチェック
        String reportError = validateReport(gv.getReport());
        if (!reportError.equals("")) {
            errors.add(reportError);
        }

        return errors;
    }

    /**
     * タイトルに入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param title タイトル
     * @return エラーメッセージ
     */
    private static String validateEmployee(EmployeeView employee) {
        if (employee == null || employee.equals("")) {
            return MessageConst.E_NOTITLE.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

    /**
     * 内容に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param content 内容
     * @return エラーメッセージ
     */
    private static String validateReport(ReportView report) {
        if (report == null || report.equals("")) {
            return MessageConst.E_NOCONTENT.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }
}