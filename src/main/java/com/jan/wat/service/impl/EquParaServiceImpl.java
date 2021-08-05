package com.jan.wat.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jan.wat.pojo.*;
import com.jan.wat.mapper.EquParaMapper;
import com.jan.wat.pojo.vo.*;
import com.jan.wat.service.IEquCommandService;
import com.jan.wat.service.IEquParaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jan.wat.pojo.EquPara;
import com.jan.wat.service.IEquParagroupService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author January
 * @since 2021-07-08
 */
@Service("EquParaServiceImpl")
public class EquParaServiceImpl extends ServiceImpl<EquParaMapper, EquPara> implements IEquParaService {
    @Autowired
    IEquParagroupService iEquParagroupService;

    @Autowired
    IEquCommandService iEquCommandService;

    @Override
    public List<ParaTree> getTree() {
        LambdaQueryWrapper<EquPara> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EquPara::getType,2);
        List<EquParagroup> group = iEquParagroupService.list();
        List<EquPara> para = list(wrapper);

        Map<Integer,Integer> map = new HashMap<>();

        List<ParaTree> tree = new ArrayList<>();

        int index = 0;
        for(EquParagroup event : group){

            ParaTree branch = new ParaTree(event);
            map.put(event.getId(),index++);
            tree.add(branch);
        }

        for(EquPara event : para){
            index = map.get(event.getParagroupId());
            tree.get(index).getChildren().add(event);
        }

        return tree;
    }



    @Autowired
    EquParaMapper mapper;

    @Override
    public List<EquParaQuery> queryParaList() {
        return mapper.queryParaList();
    }

    @Override
    public IPage<EquParaQuery> selectByPage(Page<EquParaQuery> page) {
        return mapper.selectByPage(page);
    }

    @Override
    public List<SigEuipementparaQuery> getSigEquipmentPara(String equipment_ID) {
        return mapper.getSigEquipmentPara(equipment_ID);
    }

    @Override
    public boolean sendCommand(List<SigEuipementparaQuery> queries, String userCode) {
        boolean bool = false;
        StringBuilder command = new StringBuilder("<D>");
        StringBuilder describe = new StringBuilder();
        String equipmentId;
        equipmentId = queries.get(0).getEquipment_ID();

        for(SigEuipementparaQuery query : queries){
            command.append("<P>");
            command.append(String.format("<I>%s</I>",query.getPara_ID()));
            command.append(String.format("<T>%d</T>",query.getType()));
            command.append(String.format("<V>%s</V></P>",query.getParavalue()));
            describe.append(String.format("%s(%s)",query.getPara_Name(),query.getParavalue()));
        }
        command.append("</D>");
        if(!equipmentId.equals("")){
            LambdaQueryWrapper<EquCommand> deleteWrapper = new LambdaQueryWrapper<>();
            deleteWrapper.eq(EquCommand::getEquipmentId,equipmentId)
                         .eq(EquCommand::getCommandtype,147)
                         .le(EquCommand::getStatus,2);
            iEquCommandService.remove(deleteWrapper);
            EquCommand equCommand = new EquCommand();
            equCommand.setStatus(1);
            equCommand.setDes(describe.toString());
            equCommand.setEquipmentId(equipmentId);
            equCommand.setCommand(command.toString());
            equCommand.setCommandtype(147);
            equCommand.setSettingtime(LocalDateTime.now());
            equCommand.setUsercode(userCode);
            bool = iEquCommandService.save(equCommand);
        }
        return bool;
    }

    @Override
    public List<MulEquipparaQuery> getMulEquipmentPara(List<String> equipmentIds) {
        StringBuilder from = new StringBuilder();
        StringBuilder ids = new StringBuilder("(");

        int i = 0;
        for(String id : equipmentIds){
            if(i==0){
                from.append(String.format(" ,(select para_ID FROM equ_equipmentpara a where a.Equipment_ID = \'%s\') t0 ",id));
            }else{
                from.append(String.format(" inner join (SeLeCT para_ID FROM equ_equipmentpara a where a.Equipment_ID = \'%s\') t%i on t0.para_ID=t%i.para_ID ",id,i,i));
            }
            i++;
            ids.append(id);
            if(i!=equipmentIds.size())
                ids.append(",");
        }
        ids.append(")");
        List<MulEquipparaQuery> mulEquipmentPara = mapper.getMulEquipmentPara(from.toString());

        for(MulEquipparaQuery item : mulEquipmentPara){
            int paraId = item.getPara_ID();
            if(item.getPType() == 1){
                List<LimitQuery> limit = mapper.getLimit(paraId, ids.toString());
                if(limit.size() == 1){
                    item.setUpLimit(limit.get(0).getUpLimit());
                    item.setDownLimit(limit.get(0).getDownLimit());
                }
            }
        }
       return mulEquipmentPara;
    }
}
