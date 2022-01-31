package services;

import java.util.ArrayList;
import java.util.List;

import actions.views.GoodConverter;
import actions.views.GoodView;
import models.Good;
import models.validators.GoodValidator;

/**
 * いいねテーブルの操作に関わる処理を行うクラス
 */
public class GoodService extends ServiceBase {


    /**
     * idを条件に取得したデータをReportViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public GoodView findOne(int id) {
        return GoodConverter.toView(findOneInternal(id));
    }

    /**
     * 画面から入力された日報の登録内容を元にデータを1件作成し、日報テーブルに登録する
     * @param rv 日報の登録内容
     * @return バリデーションで発生したエラーのリスト
     */
    public List<String> create(GoodView gv) {

        List<String> errors = GoodValidator.validate(gv);
        if (errors.size() == 0) {
            createInternal(gv);
        }
        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    /**
     * 画面から入力された日報の登録内容を元に、日報データを更新する
     * @param rv 日報の更新内容
     * @return バリデーションで発生したエラーのリスト
     */
    public List<String> update(GoodView gv) {
        updateInternal(gv);
        List<String> error = new ArrayList<String>();
        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        //とりあえずバリデーションは無しで
        return error;
    }

    /**
     * idを条件にデータを1件取得する
     * @param id
     * @return 取得データのインスタンス
     */
    private Good findOneInternal(int id) {
        return em.find(Good.class, id);
    }

    /**
     * 日報データを1件登録する
     * @param rv 日報データ
     */
    private void createInternal(GoodView gv) {

        em.getTransaction().begin();
        em.persist(GoodConverter.toModel(gv));
        em.getTransaction().commit();

    }

    /**
     * 日報データを更新する
     * @param rv 日報データ
     */
    private void updateInternal(GoodView gv) {

        em.getTransaction().begin();
        Good g = findOneInternal(gv.getId());
        GoodConverter.copyViewToModel(g, gv);
        em.getTransaction().commit();

    }

}