#encoding: UTF-8

class Cliente < ActiveRecord::Base
	attr_accessible :nome, :idade
	
	has_many :qualificacoes
	
	
	validates_presence_of :nome, :message => " - deve ser preenchido"
	validates_uniqueness_of :nome, :message => " - deve ser unico"
	
	validates_numericality_of 	:idade, 
								:greater_than=>0, 
								:less_than=>100, 
								:message => "ninguém é tão velho"
	
end
