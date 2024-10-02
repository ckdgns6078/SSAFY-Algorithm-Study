select item_id, item_name, rarity
from item_info 
where item_id not in (select distinct ifnull(parent_item_id,-1) from item_tree)
order by item_id desc
