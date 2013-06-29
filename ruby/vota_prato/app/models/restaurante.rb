class Restaurante < ActiveRecord::Base
	attr_accessible :nome, :endereco, :especialidade

	has_many :qualificacoes
	has_many :comentarios,	
			 :as => "comentavel"

	has_and_belongs_to_many :pratos
	
	
	
	scope :massas, where({ :especialidade => 'Massas' })
	scope :recentes, where(["created_at >= ?", 2.months.ago]);
	
	
	validates_uniqueness_of :nome, :message=> "nome ja cadastrado"
	validates_uniqueness_of :endereco, :message=> "endereco ja cadastrado"
	validates_presence_of :nome, :message=> "deve ser preenchido"
	validates_presence_of :endereco, :message=> "deve ser preenchido"
	validates_presence_of :especialidade, :message=> "deve ser preenchido"
	
	
	
	validate :primeira_letra_deve_ser_maiuscula
	
	
	
	private
	def primeira_letra_deve_ser_maiuscula
	
		errors.add("nome","primeira letra deve ser maiuscula") unless nome =~ /[A-Z].*/
		
	end
	
	
	
	
	
	
	
end
