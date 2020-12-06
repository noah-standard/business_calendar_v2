package com.end.demo.service;

import com.end.demo.dao.IUserDAO;
import com.end.demo.vo.CalendarVO;
import com.end.demo.vo.HolidayVO;
import com.end.demo.vo.UserVO;
import com.end.demo.vo.XhrVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class UserService {

    @Autowired
    TransactionTemplate transactionTemplate; //트랜잭션 설정

    @Autowired
    IUserDAO dao;

    public int insertCalendar(CalendarVO cVo) {
        try {
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    dao.insertCalendar(cVo);
                }
            });
            return 1;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("[TransactionTemplate] Rollback");
            return 0;
        }
    }

    public int insertHoliday(ArrayList<HolidayVO> hVoArray) {
        try {
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    for (HolidayVO hVo : hVoArray) {
                        dao.insertHoliday(hVo);
                    }
                }
            });
            return 1;
        } catch (Exception e) {
            System.out.println("[TransactionTemplate] Rollback");
            return 0;
        }
    }

    public List<CalendarVO> getCalendar(XhrVO values) {
        return dao.getCalendar(values);
    }

    public UserVO getUser(String userid) {
        return dao.getUser(userid);
    }

    public List<CalendarVO> getList(String name, int page) {
        final int VIEW_COUNT = 10;
        int eIndex = page * VIEW_COUNT;
        int sIndex = eIndex - (VIEW_COUNT - 1);
        return dao.getList(name, sIndex, eIndex);
    }

    public int maxCountList(String name) {
        return dao.maxCountList(name);
    }

    public int updateDelete(List<Integer> i) {
        try {
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    dao.updateDelete(i);
                }
            });
            return 1;
        } catch (Exception e) {
            System.out.println("[TransactionTemplate] Rollback");
            return 0;
        }
    }

    public CalendarVO getDetail(int i) {
        return dao.getDetail(i);
    }

    public int updateForm(CalendarVO cvo) {
        try {
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    dao.updateForm(cvo);
                }
            });
            return 1;
        } catch (Exception e) {
            System.out.println("[TransactionTemplate] Rollback");
            return 0;
        }
    }

    public UserVO getEtc(String name) {
        return dao.getEtc(name);
    }

    public int updateAgree(HashMap param) {
        try {
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    dao.updateAgree(param);
                }
            });
            return 1;
        } catch (Exception e) {
            System.out.println("[TransactionTemplate] Rollback");
            return 0;
        }
    }


}
