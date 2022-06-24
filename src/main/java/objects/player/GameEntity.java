package objects.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

public abstract class GameEntity {

    protected float x, y, velX, velY, speed;
    protected float width, height;
    protected Body body;
    protected Texture texture;

    public GameEntity(float width, float height, Body body) {
        this.x = body.getPosition().x;
        this.y = body.getPosition().y;
        this.width = width;
        this.height = height;
        this.body = body;
        this.velX = 0;
        this.velY = 0;
        this.speed = 0;
        this.texture = new Texture("objects/player.png");
    }

    public abstract void update();

    public abstract void render(SpriteBatch batch);

    public Body getBody() {
        return body;
    }
}