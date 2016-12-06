package com.hongqiunicom.crm.test;

import com.hongqiunicom.crm.dao.BroadbandDao;
import com.hongqiunicom.crm.dao.impl.BroadbandDaoImpl;
import com.hongqiunicom.crm.entity.Broadband;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by XieJiaXin on 16-11-19.
 */
public class Test {

    public static void main(String[] args) {

        BroadbandDao bbd = new BroadbandDaoImpl();
        bbd.getCriteria();

    }
}
