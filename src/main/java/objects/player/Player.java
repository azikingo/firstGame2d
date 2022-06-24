package objects.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import static helper.Constants.PPM;

public class Player extends GameEntity {

    private int jumpCounter;

    public Player(float width, float height, Body body) {
        super(width, height, body);
        this.speed = 10f;
        this.jumpCounter = 0;
    }

    @Override
    public void update() {
        x = body.getPosition().x * PPM - (width / 2);
        y = body.getPosition().y * PPM - (height / 2);

        checkUserInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }

    private void checkUserInput() {
        velX = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.D))
            velX = 1;
        if (Gdx.input.isKeyPressed(Input.Keys.A))
            velX = -1;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            velX = 1;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            velX = -1;

        if ((Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.W) || Gdx.input.isKeyJustPressed(Input.Keys.UP)) && jumpCounter < 2) {
            float force = body.getMass() * 18;
            body.setLinearVelocity(body.getLinearVelocity().x, 0);
            body.applyLinearImpulse(new Vector2(0, force), body.getPosition(), true);
            jumpCounter++;
        }

        // reset jumpCounter
        if (body.getLinearVelocity().y == 0)
            jumpCounter = 0;

        body.setLinearVelocity(velX * speed, body.getLinearVelocity().y < 25 ? body.getLinearVelocity().y : 25);
    }
}